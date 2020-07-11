package com.joojet.plugins.rewards.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.offhand.CaptainAmericaShield;
import com.joojet.plugins.mobs.fireworks.PaintTheSky;
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
	CAPTAIN_AMERICA,
	PAINT_THE_SKIES;
	
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
				this.reward = new ItemStack (Material.DIAMOND, 18);
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
				this.reward = new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 2);
				break;
			case CAPTAIN_AMERICA:
				this.reward = new CaptainAmericaShield (ChatColor.GOLD);
				break;
			case PAINT_THE_SKIES:
				this.reward = new PaintTheSky().generateFirework(64, 3);
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
