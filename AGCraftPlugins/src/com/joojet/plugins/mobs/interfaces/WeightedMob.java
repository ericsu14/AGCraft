package com.joojet.plugins.mobs.interfaces;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public class WeightedMob 
{
	/** Mob equipment this entity holds */
	private MobEquipment equipment;
	
	/** Min range */
	private int minWeight;
	
	/** Max range  */
	private int maxWeight;
	
	public WeightedMob (MobEquipment equipment, int min, int max)
	{
		this.equipment = equipment;
		this.minWeight = min;
		this.maxWeight = max;
	}
	
	/** Returns true if the random roll is within the min and max ranges for this monster */
	public boolean inRange (int roll)
	{
		return (roll >= this.minWeight && roll <= this.maxWeight);
	}
	
	/** Returns the equipment attached to this weighted mob entry */
	public MobEquipment getEquipment ()
	{
		return this.equipment;
	}
	
	/** Returns the weight's min range */
	public int getMinWeight ()
	{
		return this.minWeight;
	}
	
	/** Returns the weight's max range */
	public int getMaxWeight ()
	{
		return this.maxWeight;
	}
}

