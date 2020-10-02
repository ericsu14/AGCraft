package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.allies.horse.beatthebruins.USCHorseTypes;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.giant.beatthebruins.BruinGiantTypes;
import com.joojet.plugins.mobs.monsters.phantom.beatthebruins.BeatTheBruinPhantomTypes;
import com.joojet.plugins.mobs.monsters.phantom.beatthebruins.PhantomMenace;
import com.joojet.plugins.mobs.monsters.polar_bear.beatthebruins.BruinPolarBearTypes;
import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.CollegeSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.CollegeZombieTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class BeatTheBruinsHandler extends AbstractSpawnHandler
{	
	public BeatTheBruinsHandler (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		super (monsterTypeInterpreter, bossBarController);
		this.addMonsterTypes(new CollegeZombieTypes(this.monsterTypeInterpreter),
				new CollegeSkeletonTypes(this.monsterTypeInterpreter),
				new BruinPolarBearTypes (this.monsterTypeInterpreter),
				new BruinGiantTypes (this.monsterTypeInterpreter),
				new USCHorseTypes (this.monsterTypeInterpreter),
				new BeatTheBruinPhantomTypes (this.monsterTypeInterpreter));
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.SPAWNER_EGG, SpawnReason.REINFORCEMENTS);
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll) 
	{
		// Transforms phantoms into a helpful mob
		if (type == EntityType.PHANTOM)
		{
			MobEquipment mobEquipment = new PhantomMenace ();
			EquipmentTools.equipEntity(entity, mobEquipment, this.bossBarController);
			return;
		}
		
		if (this.reasonFilter(reason) && roll >= 0.30 && roll <= 0.60)
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
	}
	
}
