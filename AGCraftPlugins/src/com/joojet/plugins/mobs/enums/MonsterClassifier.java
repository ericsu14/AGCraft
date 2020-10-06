package com.joojet.plugins.mobs.enums;

public enum MonsterClassifier 
{
	COMMON (0.0),
	UNCOMMON (0.10),
	RARE (0.20),
	EPIC (0.35),
	LEGENDARY (0.45),
	MYTHIC (0.65);
	
	/** Required threshold needed to be reached in order for the monster under this classifier is able to naturally
	 *  spawn. */
	protected Double threshold;
	
	private MonsterClassifier (Double threshold)
	{
		this.threshold = threshold;
	}
	
	public Double getThreshold ()
	{
		return this.threshold;
	}
}
