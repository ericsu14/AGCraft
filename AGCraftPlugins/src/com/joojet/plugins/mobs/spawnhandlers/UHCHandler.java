package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.ghast.UHC.UHCGhastTypes;

public class UHCHandler extends AbstractSpawnHandler
{
	public UHCHandler (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		super (monsterTypeInterpreter, bossBarController);
		this.addMonsterTypes(new UHCGhastTypes(this.monsterTypeInterpreter));
		this.addSpawnReasons(SpawnReason.NATURAL);
	}
	
	/** Handles UHC-specific Mob Spawns */
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll)
	{		
		if (this.reasonFilter(reason))
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
	}
}
