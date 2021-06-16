package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

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
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Stores a reference to the music listener used to enable and disable
	 *  music cues for different boss fight events */
	protected MusicListener musicListener;
	/** BukkitRunnable instance updating all active boss bars every tick */
	protected BossBarTaskRunner bossTaskRunner;
	
	/** Constructs a new BossBarController instance serving as an API for any module dependent
	 *  on this controller to activate boss bars. */
	public BossBarController (MonsterTypeInterpreter monsterTypeInterpreter, MusicListener musicListener)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.musicListener = musicListener;
		this.bossTaskRunner = new BossBarTaskRunner();
	}
	
	/** Enables the BossTaskRunner to start dynamically updating all active boss bars  */
	public void onEnable ()
	{
		this.bossTaskRunner.runTaskTimer(AGCraftPlugin.plugin, 0, 1);
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
		if (!this.bossTaskRunner.containsTask(uuidKey))
		{
			BossBar bossBar = AGCraftPlugin.plugin.getServer().createBossBar(entity.getCustomName(), BarColor.RED, BarStyle.SEGMENTED_6, BarFlag.PLAY_BOSS_MUSIC);
			MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(entity);
			MusicType bossTheme = null;
			if (equipment != null && equipment.containsBossTheme())
			{
				bossTheme = equipment.getBossTheme();
			}
			this.bossTaskRunner.addBossBarTask(uuidKey, new BossBarTask (bossBar, entity, bossTheme, musicListener));
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
			if (!this.bossTaskRunner.containsTask(uuidKey))
			{
				createBossBar (bossEntity);
			}
			this.bossTaskRunner.addPlayerToBossBar(uuidKey, player);
			
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
		if (uuidKey != null && this.bossTaskRunner.containsTask(uuidKey))
		{
			this.bossTaskRunner.removePlayerFromBossBar(uuidKey, player);
			this.musicListener.soundPlayer.stopAllSoundsNearPlayer(player);
		}
	}
	
	/** Removes an active boss bar from the server.
	 * 		@param entity - Entity we are removing the boss bar from */
	public void removeBossBar (LivingEntity entity)
	{
		UUID uuidKey = getBossBarUUID (entity);
		if (uuidKey != null && this.bossTaskRunner.containsTask(uuidKey))
		{
			this.bossTaskRunner.removeBossBarTask(uuidKey);
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
}
