package com.joojet.plugins.rewards.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.scrolls.SummonFrolf;

public enum RewardType 
{
	TEST_STICK,
	PARTICIPATION_DIAMONDS,
	PARTICIPATION_FROLF;
	
	private ItemStack reward;
	
	private RewardType ()
	{
		switch (this.name())
		{
			case "TEST_STICK":
				this.reward = new ItemStack (Material.STICK, 1);
				break;
			case "PARTICIPATION_DIAMONDS":
				this.reward = new ItemStack (Material.DIAMOND, 16);
				break;
			case "PARTICIPATION_FROLF":
				this.reward = new SummonFrolf ();
				break;
			default:
				break;
		}
	}
	
	public ItemStack getReward ()
	{
		return this.reward;
	}
	
	@Override
	public String toString ()
	{
		return this.name();
	}
}
