package com.joojet.plugins.mobs.enums;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum CustomPotionEffect 
{
	STRENGTH (PotionEffectType.INCREASE_DAMAGE, 1), 
	SPEED (PotionEffectType.SPEED, 1),
	JUMP_BOOST (PotionEffectType.JUMP, 1),
	RESISTANCE (PotionEffectType.DAMAGE_RESISTANCE, 1),
	FIRE_RESISTANCE (PotionEffectType.FIRE_RESISTANCE, 1);
	
	private PotionEffect effect;
	private CustomPotionEffect (PotionEffectType effect, int amplifier)
	{
		this.effect = new PotionEffect (effect, 99999, amplifier);
	}
	
	public PotionEffect getPotionEffect ()
	{
		return this.effect;
	}
};
