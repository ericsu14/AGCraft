package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.phantom.beatthebruins.PhantomMenace;
import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.CollegeSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.CollegeZombieTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class BeatTheBruinsHandler extends AmplifiedSpawnHandler
{	
	public BeatTheBruinsHandler ()
	{
		this.addMonsterTypes(new CollegeZombieTypes(),
				new CollegeSkeletonTypes());
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.SPAWNER_EGG, SpawnReason.REINFORCEMENTS);
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll) 
	{
		// Transforms phantoms into a helpful mob
		if (type == EntityType.PHANTOM)
		{
			MobEquipment mobEquipment = new PhantomMenace ();
			EquipmentTools.equipEntity(entity, mobEquipment);
			return;
		}
		
		if (this.reasonFilter(reason) && roll >= 0.30 && roll <= 0.60)
		{
			MobEquipment mobEquipment = this.getRandomEqipment(type, biome);
			if (mobEquipment != null)
			{
				EquipmentTools.equipEntity(entity, mobEquipment);
			}
		}
	}
	
}
