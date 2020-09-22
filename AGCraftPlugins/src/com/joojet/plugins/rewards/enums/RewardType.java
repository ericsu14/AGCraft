package com.joojet.plugins.rewards.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.cake.SprinklesCake;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.offhand.BlueAndGold;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.offhand.CaptainAmericaShield;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.potions.EternalRusheeMocktail;
import com.joojet.plugins.mobs.equipment.potions.StrawberryMocktail;
import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.equipment.weapons.ShotBow;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualTravesty;
import com.joojet.plugins.mobs.fireworks.PaintTheSky;
import com.joojet.plugins.mobs.scrolls.*;

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
	STRAWBERRY_MOCKTAIL,
	BLUE_AND_GOLD,
	BRUIN_SHIELD,
	BRUIN_HEAD,
	DOOM_GUY,
	BARNEY,
	SKULL_KID,
	SPIRIT_OF_TROY,
	ETERNAL_MOCKTAIL,
	USC_CREEPER_SHIELD,
	USC_ARCHER,
	AG_SPOTTED,
	UCLA_BEAR_TAMER,
	GIANT_BRUIN_TAMER,
	TROJAN_WARRIOR,
	ETERNAL_TROJAN_ARCHER,
	JOHNNY_RUSNAK,
	SPIRITUAL_TRAVESTY,
	SPRINKLES_CAKE;
	
	public ItemStack getReward ()
	{
		switch (this)
		{
			case TEST_STICK:
				return new ItemStack (Material.STICK, 1);
			case DIAMONDS:
				return new ItemStack (Material.DIAMOND_BLOCK, 3);
			case FROLF:
				return new SummonFrolf ();
			case GOLDEN_CARROTS:
				return new ItemStack (Material.GOLDEN_CARROT, 64);
			case ADVANCED_GOLEM:
				return new SummonAdvancedGolem ();
			case JOHN_JAE:
				return new SummonJohnJae ();
			case ENCHANTED_GOLDEN_APPLE:
				return new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 5);
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
			case BLUE_AND_GOLD:
				return new BlueAndGold ();
			case BRUIN_SHIELD:
				return new BruinShield ();
			case BRUIN_HEAD:
				return new BruinHead ();
			case DOOM_GUY:
				return new SummonDoomGuy ();
			case BARNEY:
				return new SummonBarney ();
			case SKULL_KID:
				return new SummonSkullKid ();
			case SPIRIT_OF_TROY:
				return new SummonSpiritOfTroy ();
			case ETERNAL_MOCKTAIL:
				return new EternalRusheeMocktail (ChatColor.YELLOW);
			case USC_CREEPER_SHIELD:
				return new USCCreeperShield ();
			case USC_ARCHER:
				return new SummonUSCArcher();
			case AG_SPOTTED:
				return new SummonAGSpotted();
			case UCLA_BEAR_TAMER:
				return new SummonUCLABearTamer();
			case GIANT_BRUIN_TAMER:
				return new SummonGiantBruinTamer();
			case TROJAN_WARRIOR:
				return new SummonTrojanWarrior();
			case ETERNAL_TROJAN_ARCHER:
				return new SummonEternalTrojanArcher();
			case JOHNNY_RUSNAK:
				return new SummonJohnnyRusnak();
			case SPIRITUAL_TRAVESTY:
				return new SpiritualTravesty(ChatColor.GOLD);
			case SPRINKLES_CAKE:
				return new SprinklesCake ();
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
