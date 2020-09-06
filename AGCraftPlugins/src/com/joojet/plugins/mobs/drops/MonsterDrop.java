package com.joojet.plugins.mobs.drops;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/** A class that represents a simple monster drop containing an itemstack with its associated
 *  drop rate from 0.0 - 1.0. */
public class MonsterDrop 
{
	/** Material of the item to be dropped */
	protected Material material;
	/** The itemstack to be dropped */
	protected ItemStack drop;
	/** The chance this item will be dropped */
	protected double dropRate;
	/** Random number generator instance used for calculating drop chances */
	protected Random rand;
	/** Min amount of items dropped */
	protected int minAmount;
	/** Max amount of items dropped */
	protected int maxAmount;
	
	/** Creates a new instance of a monster drop that can be assigned to a
	 *  custom monster.
	 *  @param drop - The dropped item
	 *  @param dropRate - Chance of the item dropping (as a double) */
	public MonsterDrop (ItemStack drop, double dropRate)
	{
		this.drop = drop;
		this.material = drop.getType();
		this.dropRate = dropRate;
		this.minAmount = 1;
		this.maxAmount = 1;
		this.rand = new Random();
	}
	
	/** Creates a new instance of a monster drop that is based on a vanilla Minecraft
	 *  material. The itemstack instance will be generated upon calling
	 *  generateDrop.
	 *  @param material - Material of the item being dropped
	 *  @param dropRate - Chance of the item dropping (as a double)
	 *  @param minAmount - Min. amount of items to be dropped
	 *  @param maxAmount - Max. amount of items to be dropped */
	public MonsterDrop (Material material, double dropRate, int minAmount, int maxAmount)
	{
		this.drop = null;
		this.material = material;
		this.dropRate = dropRate;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.rand = new Random();
	}
	
	
	/** Performs a random roll and returns the dropped item if and only if
	 *  the random roll's value is less or equal than the assigned drop rate.
	 *  Otherwise, return NULL if the roll fails.
	 *  @param looting - The percentage of looting bonuses that can be passed
	 *                   into this call, which will add the drop rate
	 *                   by the looting percentage. */
	public ItemStack generateDrop (double looting)
	{
		ItemStack droppedItem = (this.drop != null) ? this.drop :
			new ItemStack (this.material, this.generateRandomInt());
		return (rand.nextDouble() <= (this.dropRate + looting)) ? 
				droppedItem : null;
	}
	
	/** Returns the dropped item */
	public ItemStack getDrop ()
	{
		return this.drop;
	}
	
	/** Returns the drop rate of this item */
	public double dropRate ()
	{
		return this.dropRate;
	}
	
	/** Invokes the RNG to generate a random integer between the 
	 *  passed min and max amounts */
	protected int generateRandomInt ()
	{
		return this.rand.nextInt ((this.maxAmount - this.minAmount) + 1) + this.minAmount;
	}
}
