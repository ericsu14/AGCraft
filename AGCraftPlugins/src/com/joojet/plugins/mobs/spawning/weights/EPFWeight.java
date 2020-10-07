package com.joojet.plugins.mobs.spawning.weights;

import org.bukkit.attribute.Attribute;

/** A special weight that tells the threat score calculation to search through the equipment's protection
 *  enchantments instead of attribute modifiers */
public class EPFWeight extends FairSpawnWeight {

	public EPFWeight(Double maxValue, Integer weight) 
	{
		super(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, maxValue, weight);
	}

}
