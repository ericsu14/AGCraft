package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCFootballTunic;
import com.joojet.plugins.mobs.equipment.head.USCTrojan;
import com.joojet.plugins.mobs.equipment.leggings.USCFootballTrousers;
import com.joojet.plugins.mobs.equipment.weapons.TrojanSword;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class USCWarrior extends USCFaction
{
	public USCWarrior ()
	{
		super (MonsterType.USC_WARRIOR);
		this.name = ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
				+ ChatColor.GOLD + " Trojan";
		this.color = ChatColor.GOLD;
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED,
				CustomPotionEffect.RESISTANCE);
		this.helmet = new USCTrojan (this.color);
		this.chestplate = new USCFootballTunic (this.color);
		this.leggings = new USCFootballTrousers (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new TrojanSword (this.color);
	}
}
