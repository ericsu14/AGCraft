package com.joojet.plugins.mobs.bossbar;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.metadata.BossMetadata;

public class BossBarAPI 
{
	/** Stores a static table of all active custom boss bars in this server */
	public static HashMap <UUID, BossBarNode> activeBossBars = new HashMap <UUID, BossBarNode> ();
	
	/** Attempts to create a new Boss Bar for the passed living entity
	 * 		@param entity - The Living entity we are creating the boss bar for */
	public static void createBossBar (LivingEntity entity)
	{
		BossBar bossBar = AGCraftPlugin.plugin.getServer().createBossBar(entity.getCustomName(), BarColor.RED, BarStyle.SEGMENTED_6, BarFlag.PLAY_BOSS_MUSIC);
		UUID uuidKey = getBossBarUUID (entity);
		if (uuidKey == null)
		{
			uuidKey = entity.getUniqueId();
			setBossMetadataOnEntity (entity);
		}
		
		// Don't create a boss bar if the entity has one already
		if (!activeBossBars.containsKey(uuidKey))
		{
			activeBossBars.put(uuidKey, new BossBarNode (bossBar, entity, uuidKey));
			activateBossBar (uuidKey);
		}
	}
	
	/** Makes the entity's boss bar referenced by its passed UUID functional by creating a new
	 *  BossBarTask runnable in this plugin. This task dynamically scales the boss bar's
	 *  health to the attached entity's current health points. */
	public static void activateBossBar (UUID uuidKey)
	{
		BossBarNode bossNode = activeBossBars.get(uuidKey);
		if (bossNode != null)
		{
			new BossBarTask (bossNode).runTaskTimer(AGCraftPlugin.plugin, 0, 1);
		}
	}
	
	/** Adds a player to the boss entity's boss bar
	 * 		@param player - Player being attached to the entity's boss bar
	 * 		@param bossEntity - The Living Entity we are attaching the player to */
	public static void addPlayerToBossBar (Player player, LivingEntity bossEntity)
	{
		UUID uuidKey = getBossBarUUID (bossEntity);
		if (uuidKey != null && activeBossBars.containsKey(uuidKey)
				&& !activeBossBars.get(uuidKey).bossBar.getPlayers().contains(player))
		{
			activeBossBars.get(uuidKey).bossBar.addPlayer(player);
			activateBossBar (uuidKey);
		}
	}
	
	/** Removes an active boss bar from the server.
	 * 		@param entity - Entity we are removing the boss bar from */
	public static void removeBossBar (LivingEntity entity)
	{
		UUID uuidKey = getBossBarUUID (entity);
		BossBarNode entry;
		if (uuidKey != null && activeBossBars.containsKey(uuidKey))
		{
			entry = activeBossBars.get(uuidKey);
			entry.bossBar.removeAll();
			if (entry.hasActiveTask())
			{
				entry.task.cancel();
			}
			activeBossBars.remove(uuidKey);
		}
	}
	
	/** Gets the Boss Bar UUID key from the Living Entity's metadata */
	public static UUID getBossBarUUID (LivingEntity entity)
	{
		PersistentDataContainer metadata = entity.getPersistentDataContainer();
		NamespacedKey key = BossMetadata.generateGenericNamespacedKey();
		if (metadata.has(key, PersistentDataType.STRING))
		{
			return UUID.fromString(metadata.get(key, PersistentDataType.STRING));
		}
		return null;
	}
	
	/** Attaches the entity's UUID as a new metadata for identifying custom boss bar events */
	public static void setBossMetadataOnEntity (LivingEntity entity)
	{
		PersistentDataContainer metadata = entity.getPersistentDataContainer();
		NamespacedKey key = BossMetadata.generateGenericNamespacedKey();
		metadata.set(key, PersistentDataType.STRING, entity.getUniqueId().toString());
	}
}
