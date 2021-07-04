package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.creeper.julyfourth.JulyFourthCreeperTypes;
import com.joojet.plugins.mobs.monsters.phantom.julyfourth.JulyFourthPhantomTypes;
import com.joojet.plugins.mobs.monsters.pillager.julyfourth.PatrioticPillagerTypes;
import com.joojet.plugins.mobs.monsters.skeleton.julyfourth.PatrioticSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.julyfourth.PatrioticZombieTypes;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;

public class JulyFourthHandler extends AbstractSpawnHandler
{
	/** The key used to reference this handler's spawn chance variable from the config file*/
	public static final String JULY_FOURTH_HANDLER_KEY = "july-fourth-spawn-chance";
	
	public JulyFourthHandler (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, 
			BossBarController bossBarController, MobSkillRunner mobSkillRunner)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, bossBarController, mobSkillRunner, JULY_FOURTH_HANDLER_KEY);
		this.addMonsterTypes(new PatrioticZombieTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter), 
				new PatrioticSkeletonTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new PatrioticPillagerTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new JulyFourthPhantomTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new JulyFourthCreeperTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter));
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.SPAWNER_EGG, SpawnReason.REINFORCEMENTS,
				SpawnReason.DEFAULT, SpawnReason.DISPENSE_EGG, SpawnReason.LIGHTNING, SpawnReason.PATROL,
				SpawnReason.TRAP);
	}
	
	/** Handles 4th of july mob spawns */
	protected void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome)
	{
		
		// Summon a new fourth of july mob when roll is between a certain range
		// or entity is a phantom
		if (this.canSpawn(reason) || type == EntityType.PHANTOM || type == EntityType.CREEPER)
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
	}

}
