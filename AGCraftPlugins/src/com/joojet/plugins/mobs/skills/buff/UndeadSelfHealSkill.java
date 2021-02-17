package com.joojet.plugins.mobs.skills.buff;

import org.bukkit.potion.PotionEffectType;

public class UndeadSelfHealSkill extends SelfHealSkill 
{
	/** Creates an undead self heal skill, allowing the undead mob to self heal once its health reaches below a certain threshold
	 *  @param potionStrength - Strength level of the healing effect
	 *  @param cooldown - Skill's cooldown
	 *  @param weight - Skill's priority weight
	 *  @param threshold - Min. threshold where the caster's health needs to be in order to use this skill */
	public UndeadSelfHealSkill(int potionStrength, int cooldown, int weight, double threshold) 
	{
		super(PotionEffectType.HARM, potionStrength, cooldown, weight, threshold);
	}

}
