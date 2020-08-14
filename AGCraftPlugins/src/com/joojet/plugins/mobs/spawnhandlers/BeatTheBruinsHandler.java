package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.CollegeSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.CollegeZombieTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class BeatTheBruinsHandler extends AmplifiedSpawnHandler
{
	/** College Zombie types */
	private CollegeZombieTypes collegeZombies;
	/** College Skeleton types */
	private CollegeSkeletonTypes collegeSkeletons;
	
	public BeatTheBruinsHandler ()
	{
		this.collegeZombies = new CollegeZombieTypes ();
		this.collegeSkeletons = new CollegeSkeletonTypes ();
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.SPAWNER_EGG, SpawnReason.REINFORCEMENTS);
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll) 
	{
		if (this.reasonFilter(reason) && roll >= 0.30 && roll <= 0.60)
		{
			MobEquipment mobEquipment;
			switch (type)
			{
				case ZOMBIE:
					mobEquipment = this.collegeZombies.getRandomEquipment(biome);
					break;
				case SKELETON:
					mobEquipment = this.collegeSkeletons.getRandomEquipment(biome);
					break;
				case HUSK:
					mobEquipment = this.collegeZombies.getRandomEquipment(biome);
					break;
				case STRAY:
					mobEquipment = this.collegeSkeletons.getRandomEquipment(biome);
					break;
				default:
					return;
			}
			EquipmentTools.equipEntity(entity, mobEquipment);
		}
	}
	
}
