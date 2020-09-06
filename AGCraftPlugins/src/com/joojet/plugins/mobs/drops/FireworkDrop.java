package com.joojet.plugins.mobs.drops;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.fireworks.FireworkTypes;

public class FireworkDrop extends MonsterDrop 
{
	/** Used to generate a random firework drop */
	protected FireworkTypes fireworkGenerator;
	
	/** Creates a custom firework drop
	 * 	@param dropRate - Drop rate of firework
	 * 	@param minAmount - Min amount of fireworks dropped
	 *  @param maxAmount - Max amount of fireworks dropped */
	public FireworkDrop (double dropRate, int minAmount, int maxAmount)
	{
		super (Material.FIREWORK_ROCKET, dropRate, minAmount, maxAmount);
		this.fireworkGenerator = new FireworkTypes ();
	}
	
	@Override
	/** Performs a random roll and returns a random custom firework if and only if
	 *  the random roll's value is less or equal than the assigned drop rate.
	 *  Otherwise, return NULL if the roll fails.
	 *  @param looting - The percentage of looting bonuses that can be passed
	 *                   into this call, which will add the drop rate
	 *                   by the looting percentage. */
	public ItemStack generateDrop (double looting)
	{
		ItemStack droppedItem = this.fireworkGenerator.getRandomFirework(this.generateRandomInt(), 3);
		return (rand.nextDouble() <= (this.dropRate + looting)) ? 
				droppedItem : null;
	}
}
