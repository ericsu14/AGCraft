package com.joojet.plugins.mobs.interfaces;

import java.util.List;

import com.joojet.plugins.mobs.skills.AbstractSkill;

public interface CustomSkillUser 
{
	/** Loads new instances of custom monster skills into an active skills list for that mob
	 * 	@param skills - A list of skills that are to be loaded with mob-specific custom skills */
	public void loadCustomSkills (List <AbstractSkill> skills);
}
