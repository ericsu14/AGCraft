package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataHolder;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.BossMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.music.enums.MusicType;

public class BossBarController 
{
	/** Stores a static table of all active custom boss bars in this server */
	public ConcurrentHashMap <UUID, BossBarNode> activeBossBars;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Stores a reference to the music listener used to enable and disable
	 *  music cues for different boss fight events */
	protected MusicListener musicListener;
	
	public BossBarController (MonsterTypeInterpreter monsterTypeInterpreter, MusicListener musicListener)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.musicListener = musicListener;
		this.activeBossBars = new ConcurrentHashMap <UUID, BossBarNode> ();
	}
	
	/** Attempts to create a new Boss Bar for the passed living entity
	 * 		@param entity - The Living entity we are creating the boss bar for */
	public void createBossBar (LivingEntity entity)
	{
		if (entity == null || entity.getType() == EntityType.PLAYER)
		{
			return;
		}
		
		UUID uuidKey = getBossBarUUID (entity);
		if (uuidKey == null)
		{
			uuidKey = entity.getUniqueId();
			setBossMetadataOnEntity (entity);
		}
		if (!activeBossBars.containsKey(uuidKey))
		{
			BossBar bossBar = AGCraftPlugin.plugin.getServer().createBossBar(entity.getCustomName(), BarColor.RED, BarStyle.SEGMENTED_6, BarFlag.PLAY_BOSS_MUSIC);
			MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(entity);
			MusicType bossTheme = null;
			if (equipment != null && equipment.containsBossTheme())
			{
				bossTheme = equipment.getBossTheme();
			}
			activeBossBars.put(uuidKey, new BossBarNode (bossBar, entity, uuidKey, bossTheme));
			activateBossBar (uuidKey);
		}
		else
		{
			BossBarNode entry = activeBossBars.get(uuidKey);
			entry.entity = entity;
			activeBossBars.replace(uuidKey, entry);
		}
	}
	
	/** Makes the entity's boss bar referenced by its passed UUID functional by creating a new
	 *  BossBarTask runnable in this plugin. This task dynamically scales the boss bar's
	 *  health to the attached entity's current health points. */
	public void activateBossBar (UUID uuidKey)
	{
		BossBarNode bossNode = activeBossBars.get(uuidKey);
		if (bossNode != null && !bossNode.hasActiveTask())
		{
			new BossBarTask (bossNode, this, this.musicListener).runTaskTimer(AGCraftPlugin.plugin, 0, 1);
		}
	}
	
	/** Adds a player to the boss entity's boss bar
	 * 		@param player - Player being attached to the entity's boss bar
	 * 		@param bossEntity - The Living Entity we are attaching the player to */
	public void addPlayerToBossBar (Player player, LivingEntity bossEntity)
	{
		UUID uuidKey = getBossBarUUID (bossEntity);
		if (uuidKey != null && bossEntity.getType() != EntityType.PLAYER)
		{
			if (!activeBossBars.containsKey(uuidKey))
			{
				createBossBar (bossEntity);
			}
			activeBossBars.get(uuidKey).bossBar.addPlayer(player);
			
			// Attempts to play the entity's boss music if it exists
			MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(bossEntity);
			if (equipment != null && equipment.containsBossTheme())
			{
				this.musicListener.soundPlayer.playBossMusicNearPlayer(equipment.getBossTheme(), player, bossEntity.getUniqueId(), musicListener.musicVolume);
			}
		}
	}
	
	/** Removes a player from the boss entity's boss bar */
	public void removePlayerFromBossBar (Player player, LivingEntity bossEntity)
	{
		UUID uuidKey = getBossBarUUID (bossEntity);
		if (uuidKey != null && activeBossBars.containsKey(uuidKey))
		{
			activeBossBars.get(uuidKey).bossBar.removePlayer(player);
			this.musicListener.soundPlayer.stopAllSoundsNearPlayer(player);
		}
	}
	
	/** Removes an active boss bar from the server.
	 * 		@param entity - Entity we are removing the boss bar from */
	public void removeBossBar (LivingEntity entity)
	{
		UUID uuidKey = getBossBarUUID (entity);
		BossBarNode entry;
		if (uuidKey != null && activeBossBars.containsKey(uuidKey))
		{
			entry = activeBossBars.get(uuidKey);
			if (entry != null)
			{
				if (entry.hasActiveTask())
				{
					entry.task.cleanup();
					entry.task.cancel();
				}
				activeBossBars.remove(uuidKey);
			}
		}
	}
	
	/** Gets the Boss Bar UUID key from the Living Entity's metadata */
	public UUID getBossBarUUID (LivingEntity entity)
	{
		String entityUUID = new BossMetadata().getStringMetadata((PersistentDataHolder) entity);
		if (entityUUID != null)
		{
			return UUID.fromString(entityUUID);
		}
		return null;
	}
	
	/** Attaches the entity's UUID as a new metadata for identifying custom boss bar events */
	public void setBossMetadataOnEntity (LivingEntity entity)
	{
		new BossMetadata(entity.getUniqueId()).addStringMetadata((PersistentDataHolder) entity);
	}
	
	/** Cleans up and disables all active boss bars */
	public void cleanup ()
	{
		LivingEntity ent;
		if (activeBossBars == null)
		{
			return;
		}
		
		for (BossBarNode node : activeBossBars.values())
		{
			if (node != null)
			{
				ent = node.entity;
				if (ent != null)
				{
					removeBossBar (ent);
				}
			}
		}
		activeBossBars.clear();
	}
}
