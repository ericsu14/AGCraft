package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.interfaces.AmplifiedSpawnHandler;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.zombie.CollegeZombieTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class BeatTheBruinsHandler extends AmplifiedSpawnHandler
{
	/** College Zombie types */
	private CollegeZombieTypes collegeZombies;
	
	public BeatTheBruinsHandler ()
	{
		this.collegeZombies = new CollegeZombieTypes ();
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll) 
	{
		if (reason != SpawnReason.RAID && roll >= 0.30 && roll <= 0.50)
		{
			MobEquipment mobEquipment;
			switch (type)
			{
				case ZOMBIE:
					mobEquipment = this.collegeZombies.getRandomEquipment(biome);
					break;
				default:
					return;
			}
			EquipmentTools.equipEntity(entity, mobEquipment);
		}
	}
	
}
