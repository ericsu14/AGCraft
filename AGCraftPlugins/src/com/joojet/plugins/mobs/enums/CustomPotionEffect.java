package com.joojet.plugins.mobs.enums;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum CustomPotionEffect 
{
	STRENGTH (PotionEffectType.INCREASE_DAMAGE, 0), 
	SPEED (PotionEffectType.SPEED, 0),
	JUMP_BOOST (PotionEffectType.JUMP, 0),
	RESISTANCE (PotionEffectType.DAMAGE_RESISTANCE, 0),
	FIRE_RESISTANCE (PotionEffectType.FIRE_RESISTANCE, 0),
	STRENGTH_II (PotionEffectType.INCREASE_DAMAGE, 1),
	RESISTANCE_II (PotionEffectType.DAMAGE_RESISTANCE, 1),
	REGEN (PotionEffectType.REGENERATION, 0),
	WATER_BREATHING (PotionEffectType.WATER_BREATHING, 0);
	
	private PotionEffect effect;
	private CustomPotionEffect (PotionEffectType effect, int amplifier)
	{
		this.effect = new PotionEffect (effect, 9999999, amplifier);
	}
	
	public PotionEffect getPotionEffect ()
	{
		return this.effect;
	}
};
