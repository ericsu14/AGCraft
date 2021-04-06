package com.joojet.plugins.mobs.enums;

import org.bukkit.Material;

public enum ThrowablePotionType 
{
	/** Splash Potion */
	SPLASH (Material.SPLASH_POTION),
	/** Lingering Potion*/
	LINGERING (Material.LINGERING_POTION);
	
	/** Material used to make up the throwable potion entity */
	protected Material material;
	
	private ThrowablePotionType (Material material)
	{
		this.material = material;
	}
	
	/** Returns the material that makes up the throwable potion type */
	public Material getMaterial ()
	{
		return this.material;
	}
}
