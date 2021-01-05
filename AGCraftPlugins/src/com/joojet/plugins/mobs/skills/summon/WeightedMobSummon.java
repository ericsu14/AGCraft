package com.joojet.plugins.mobs.skills.summon;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.util.WeightedEntry;

public class WeightedMobSummon extends WeightedEntry<MonsterType> 
{
	/** Stores the type of LivingEntity this summon should be spawned as */
	protected EntityType entityType;
	
	public WeightedMobSummon(MonsterType entry, EntityType entityType, int minWeight, int maxWeight) 
	{
		super(entry, minWeight, maxWeight);
		this.entityType = entityType;
	}
	
	/** Returns the EntityType this custom monster summon should be spawned as */
	public EntityType getEntityType ()
	{
		return this.entityType;
	}

}
