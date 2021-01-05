package com.joojet.plugins.mobs.metadata;

import com.joojet.plugins.mobs.enums.MonsterType;

/** Marks this entity as one that is summoned by a custom summoning skill.
 *  Monsters marked with this metadata tag are not able to use this summoning skill. */
public class SkillSummonedMetadata extends AbstractMetadata<String> {
	
	public static String SKILL_SUMMONED_TAG = "skill-summoned-tag";
	
	public SkillSummonedMetadata ()
	{
		super(SKILL_SUMMONED_TAG, "");
	}
	
	/** Creates a new metadata tag that contains the custom monster that summoned it as its value
	 *  @param value Type of custom monster that summoned this entity with the tag */
	public SkillSummonedMetadata(MonsterType value) 
	{
		super(SKILL_SUMMONED_TAG, value.toString());
	}

}
