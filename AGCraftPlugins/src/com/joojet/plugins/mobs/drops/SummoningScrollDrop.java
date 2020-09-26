package com.joojet.plugins.mobs.drops;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.SummonTypes;

public class SummoningScrollDrop extends MonsterDrop 
{
	/** Stores the SummonType enum that identifies 
	 *  what summoning scroll should be dropped */
	protected SummonTypes summon;
	
	/** Creates a new monster drop for a summoning scroll
	 *  @param summon - SummonType enum identifying what summoning scroll should be dropped
	 *  @param dropChance - Drop rate for the summoning scroll */
	public SummoningScrollDrop (SummonTypes summon, double dropChance)
	{
		super (Material.PAPER, dropChance, 1, 1);
		this.summon = summon;
	}
	
	/** Generates the stored summoning scroll as an itemstack
	 *  if the random roll check passes. Otherwise, return null. */
	@Override
	public ItemStack generateDrop (double looting)
	{
		return (this.rand.nextDouble() <= (this.dropRate + looting)) 
				? this.summon.getSummon() : null;
	}
}
