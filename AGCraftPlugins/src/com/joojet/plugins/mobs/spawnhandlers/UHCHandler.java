package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.ghast.UHC.UHCGhastTypes;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;

public class UHCHandler extends AbstractSpawnHandler
{
	/** The key used to reference this handler's spawn chance variable from the config file*/
	public static final String UHC_HANDLER_KEY = "uhc-handler-spawn-chance";
	
	public UHCHandler (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, 
			BossBarController bossBarController, MobSkillRunner mobSkillRunner)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, bossBarController, mobSkillRunner, UHC_HANDLER_KEY);
		this.addMonsterTypes(new UHCGhastTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter));
		this.addSpawnReasons(SpawnReason.NATURAL);
	}
	
	/** Handles UHC-specific Mob Spawns */
	protected void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome)
	{		
		if (this.canSpawn(reason))
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
	}
}
