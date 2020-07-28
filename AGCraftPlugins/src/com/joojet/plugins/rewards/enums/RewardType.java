package com.joojet.plugins.rewards.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.offhand.CaptainAmericaShield;
import com.joojet.plugins.mobs.equipment.potions.StrawberryMocktail;
import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.equipment.weapons.ShotBow;
import com.joojet.plugins.mobs.fireworks.PaintTheSky;
import com.joojet.plugins.mobs.scrolls.SummonAdvancedGolem;
import com.joojet.plugins.mobs.scrolls.SummonCookie;
import com.joojet.plugins.mobs.scrolls.SummonFrolf;
import com.joojet.plugins.mobs.scrolls.SummonJohnJae;
import com.joojet.plugins.mobs.scrolls.SummonSnowball;

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
	PAINT_THE_SKIES,
	FIREWORK_LAUNCHER,
	COOKIE,
	SNOWBALL,
	SHOTBOW,
	STRAWBERRY_MOCKTAIL;
	
	public ItemStack getReward ()
	{
		switch (this)
		{
			case TEST_STICK:
				return new ItemStack (Material.STICK, 1);
			case DIAMONDS:
				return new ItemStack (Material.DIAMOND_BLOCK, 2);
			case FROLF:
				return new SummonFrolf ();
			case GOLDEN_CARROTS:
				return new ItemStack (Material.GOLDEN_CARROT, 64);
			case ADVANCED_GOLEM:
				return new SummonAdvancedGolem ();
			case JOHN_JAE:
				return new SummonJohnJae ();
			case ENCHANTED_GOLDEN_APPLE:
				return new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 2);
			case CAPTAIN_AMERICA:
				return new CaptainAmericaShield (ChatColor.GOLD);
			case PAINT_THE_SKIES:
				return new PaintTheSky().generateFirework(64, 2);
			case FIREWORK_LAUNCHER:
				return new FireworkLauncher(ChatColor.GOLD);
			case COOKIE:
				return new SummonCookie ();
			case SNOWBALL:
				return new SummonSnowball ();
			case SHOTBOW:
				return new ShotBow (ChatColor.GOLD);
			case STRAWBERRY_MOCKTAIL:
				return new StrawberryMocktail (ChatColor.LIGHT_PURPLE);
			default:
				break;
		}
		return null;
	}
	
	@Override
	public String toString ()
	{
		return this.name();
	}
}
