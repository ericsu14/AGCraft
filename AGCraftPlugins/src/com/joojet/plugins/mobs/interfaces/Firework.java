package com.joojet.plugins.mobs.interfaces;

import org.bukkit.inventory.ItemStack;

public abstract class Firework 
{
	
	/** Generates a new firework. To be overridden by firework implementations
	 * 	@param amount - Number of fireworks distributed
	 * 	@param power - Total power of the firework */
	public abstract ItemStack generateFirework (int amount, int power);
}
