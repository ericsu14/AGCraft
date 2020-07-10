package com.joojet.plugins.rewards.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.offhand.CaptainAmericaShield;
import com.joojet.plugins.mobs.scrolls.SummonAdvancedGolem;
import com.joojet.plugins.mobs.scrolls.SummonFrolf;
import com.joojet.plugins.mobs.scrolls.SummonJohnJae;

public enum RewardType 
{
	TEST_STICK,
	DIAMONDS,
	FROLF,
	GOLDEN_CARROTS,
	ADVANCED_GOLEM,
	JOHN_JAE,
	ENCHANTED_GOLDEN_APPLE,
	CAPTAIN_AMERICA;
	
	private ItemStack reward;
	
	private RewardType ()
	{

	}
	
	public ItemStack getReward ()
	{
		switch (this)
		{
			case TEST_STICK:
				this.reward = new ItemStack (Material.STICK, 1);
				break;
			case DIAMONDS:
				this.reward = new ItemStack (Material.DIAMOND, 16);
				break;
			case FROLF:
				this.reward = new SummonFrolf ();
				break;
			case GOLDEN_CARROTS:
				this.reward = new ItemStack (Material.GOLDEN_CARROT, 64);
				break;
			case ADVANCED_GOLEM:
				this.reward = new SummonAdvancedGolem ();
				break;
			case JOHN_JAE:
				this.reward = new SummonJohnJae ();
				break;
			case ENCHANTED_GOLDEN_APPLE:
				this.reward = new ItemStack (Material.GOLDEN_APPLE, 2);
				break;
			case CAPTAIN_AMERICA:
				this.reward = new CaptainAmericaShield (ChatColor.GOLD);
				break;
			default:
				break;
		}
		return this.reward;
	}
	
	@Override
	public String toString ()
	{
		return this.name();
	}
}
