package com.joojet.plugins.mobs.skills.runnable;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.SkillSummonedMetadata;
import com.joojet.plugins.mobs.skills.summon.AbstractSummonSkill;
import com.joojet.plugins.mobs.skills.summon.WeightedMobSummon;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class SummonEntitiesTask extends BukkitRunnable
{
	/** The type of custom mobs that can be summoned at a time */
	protected List <WeightedMobSummon> mobPool;
	/** A list of locations entities can be summoned at */
	protected List <Location> spawnLocations;
	/** A reference to the AbstractSummonSkill instance launching this task */
	protected AbstractSummonSkill skillInstance;
	/** A reference to the MonsterTypeInterpreter to grab custom monster data for the summon */
	protected MonsterTypeInterpreter mobInterpreter;
	/** A reference to the plugin's boss bar controller */
	protected BossBarController bossBarController;
	/** Reference to the damage display listener */
	protected DamageDisplayListener damageDisplayListener;
	/** Keeps track of the max spawn weight used to init. the RNG */
	private int maxSpawnWeight;
	
	public SummonEntitiesTask (List <Location> spawnLocations, AbstractSummonSkill skillInstance, MonsterTypeInterpreter mobInterpreter,
			BossBarController bossBarController, DamageDisplayListener damageDisplayListener)
	{
		this.spawnLocations = spawnLocations;
		this.skillInstance = skillInstance;
		this.mobPool = skillInstance.getSummons();
		this.mobInterpreter = mobInterpreter;
		this.bossBarController = bossBarController;
		this.damageDisplayListener = damageDisplayListener;
		this.maxSpawnWeight = skillInstance.getMaxSpawnWeight();
	}

	@Override
	public void run() 
	{
		if (!this.spawnLocations.isEmpty())
		{
			// First, select a location in the list
			Location spawnLocation = this.spawnLocations.remove(0);
			
			// Secondly, randomly select a monster from the mob pool to spawn
			int roll = this.skillInstance.getRandomGenerator().nextInt(this.maxSpawnWeight);
			WeightedMobSummon summon = (WeightedMobSummon) WeightedMobSummon.searchWeightedListForWeightedEntry(roll, this.mobPool);
			
			// Finally, summon the custom monster into the world and play its custom animations
			LivingEntity summonedEntity = (LivingEntity) spawnLocation.getWorld().spawnEntity(spawnLocation, summon.getEntityType());
			// Mark this entity as "SkillSummoned", preventing it from using any summon-entity skills.
			new SkillSummonedMetadata (summon.getEntry()).addStringMetadata(summonedEntity);
			EquipmentTools.equipEntity(summonedEntity, this.mobInterpreter.searchTrie(summon.getEntry().toString()), this.bossBarController);
			this.skillInstance.playSummonAnimation(summonedEntity, this.damageDisplayListener);
		}
		else
		{
			this.cancel();
		}
		
	}
	
	
}
