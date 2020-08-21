package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCBandUniformTop;
import com.joojet.plugins.mobs.equipment.head.USCBandHead;
import com.joojet.plugins.mobs.equipment.leggings.USCBandUniformBottom;
import com.joojet.plugins.mobs.equipment.offhand.RightCrashSymbol;
import com.joojet.plugins.mobs.equipment.weapons.LeftCrashSymbol;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class SpiritOfTroy extends USCFaction 
{
	public SpiritOfTroy ()
	{
		super (MonsterType.SPIRIT_OF_TROY);
		this.addBiomes(Biome.THE_VOID);
		this.name = "The " + ChatColor.GOLD + "Spirit " + ChatColor.RED + "of " + ChatColor.GOLD + "Troy";
		this.color = ChatColor.RED;
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.health = 12;
		this.helmet = new USCBandHead (this.color);
		this.chestplate = new USCBandUniformTop (this.color);
		this.leggings = new USCBandUniformBottom (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new LeftCrashSymbol (this.color);
		this.offhand = new RightCrashSymbol (this.color);
	}
}
