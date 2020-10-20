package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.allies.horse.beatthebruins.USCHorseTypes;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
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
	/** The key used to reference this handler's spawn chance variable from the config file*/
	public static final String BEAT_THE_BRUINS_HANDLER_KEY = "beat-the-bruins-spawn-chance";
	
	public BeatTheBruinsHandler (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, BossBarController bossBarController)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, bossBarController, BEAT_THE_BRUINS_HANDLER_KEY);
		this.addMonsterTypes(new CollegeZombieTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new CollegeSkeletonTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new BruinPolarBearTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new BruinGiantTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new USCHorseTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new BeatTheBruinPhantomTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter));
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.SPAWNER_EGG, SpawnReason.REINFORCEMENTS);
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome) 
	{
		// Transforms phantoms into a helpful mob
		if (type == EntityType.PHANTOM)
		{
			MobEquipment mobEquipment = new PhantomMenace ();
			EquipmentTools.equipEntity(entity, mobEquipment, this.bossBarController);
			return;
		}
		
		if (this.canSpawn(reason))
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
	}
	
}
