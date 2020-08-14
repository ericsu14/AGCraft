package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.ghast.UHC.UHCGhastTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class UHCHandler extends AmplifiedSpawnHandler
{
	private UHCGhastTypes uhcGhastTypes;
	
	public UHCHandler ()
	{
		this.uhcGhastTypes = new UHCGhastTypes();
		this.addSpawnReasons(SpawnReason.NATURAL);
	}
	
	/** Handles UHC-specific Mob Spawns */
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll)
	{
		// Filter out mobs that does not satisfy our list of allowed spawn reasons
		if (!reasonFilter (reason))
		{
			return;
		}
		
		MobEquipment mobEquipment;
		switch (type)
		{
			case GHAST:
				mobEquipment = this.uhcGhastTypes.getRandomEquipment(biome);
				break;
			default:
				return;
		}
		
		EquipmentTools.equipEntity(entity, mobEquipment);
		
	}
}
