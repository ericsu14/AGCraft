package com.joojet.plugins.mobs.enums;

public enum MonsterClassifier 
{
	COMMON (0.0),
	UNCOMMON (0.15),
	RARE (0.30),
	EPIC (0.45),
	LEGENDARY (0.60),
	MYTHIC (0.80);
	
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
