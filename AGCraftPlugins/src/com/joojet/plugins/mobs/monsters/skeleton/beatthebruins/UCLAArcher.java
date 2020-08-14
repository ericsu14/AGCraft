package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class UCLAArcher extends UCLAFaction
{
	public UCLAArcher ()
	{
		super (MonsterType.UCLA_ARCHER);
		this.name = "The " + ChatColor.AQUA + "U" + ChatColor.GOLD + "C"
				+ ChatColor.AQUA + "L" + ChatColor.GOLD + "A" + " Archer";
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.color = ChatColor.AQUA;
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.weapon = new SpiritualFantasy (this.color);
	}
}
