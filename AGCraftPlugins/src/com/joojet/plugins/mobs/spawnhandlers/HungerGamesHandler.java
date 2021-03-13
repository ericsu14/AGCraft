package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.skeleton.hungergames.HGSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.hungergames.HGZombieTypes;

public class HungerGamesHandler extends AbstractSpawnHandler 
{
	/** The key used to reference this handler's spawn chance variable from the config file*/
	public static final String HUNGER_GAMES_SPAWN_HANDLER_KEY = "hunger-games-spawn-chance";
	
	public HungerGamesHandler(MonsterTypeInterpreter monsterTypeInterpreter,
			SummoningScrollInterpreter summonTypeInterpreter, BossBarController bossBarController) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, bossBarController, HUNGER_GAMES_SPAWN_HANDLER_KEY);
		
		this.addMonsterTypes(new HGZombieTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new HGSkeletonTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter));
	}

	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome) 
	{
		if (this.canSpawn(reason))
		{
			this.transformLivingEntityIntoAmplifiedMob(entity, type, reason, biome);
		}
	}

}
