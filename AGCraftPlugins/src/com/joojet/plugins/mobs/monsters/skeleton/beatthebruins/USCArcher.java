package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCFootballTunic;
import com.joojet.plugins.mobs.equipment.head.USCTrojan;
import com.joojet.plugins.mobs.equipment.leggings.USCFootballTrousers;
import com.joojet.plugins.mobs.equipment.weapons.FightOn;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class USCArcher extends USCFaction
{
	public USCArcher ()
	{
		super (MonsterType.USC_ARCHER);
		this.name = ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
				+ ChatColor.GOLD + " Bowman";
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.GOLD;
		this.helmet = new USCTrojan (this.color);
		this.chestplate = new USCFootballTunic (this.color);
		this.leggings = new USCFootballTrousers (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new FightOn (this.color);
	}
}