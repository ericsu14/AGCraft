package com.joojet.plugins.mobs.enums;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum CustomPotionEffect 
{
	STRENGTH (new PotionEffect (PotionEffectType.INCREASE_DAMAGE, 1, 99999)), 
	SPEED (new PotionEffect (PotionEffectType.SPEED, 1, 99999)),
	JUMP_BOOST (new PotionEffect (PotionEffectType.JUMP, 1, 99999)),
	RESISTANCE (new PotionEffect (PotionEffectType.DAMAGE_RESISTANCE, 1, 99999)),
	FIRE_RESISTANCE (new PotionEffect (PotionEffectType.FIRE_RESISTANCE, 1, 99999));
	
	private PotionEffect effect;
	private CustomPotionEffect (PotionEffect effect)
	{
		this.effect = effect;
	}
	
	public PotionEffect getPotionEffect ()
	{
		return this.effect;
	}
};
