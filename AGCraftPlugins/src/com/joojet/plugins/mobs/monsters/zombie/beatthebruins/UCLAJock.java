package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class UCLAJock extends UCLAFaction
{
	public UCLAJock ()
	{
		super (MonsterType.UCLA_JOCK);
		this.setStat(MonsterStat.HEALTH, 14.0);
		
		this.name = "The " + ChatColor.AQUA + "U" + ChatColor.GOLD + "C"
				+ ChatColor.AQUA + "L" + ChatColor.GOLD + "A" + ChatColor.AQUA + " Jock";
		this.color = ChatColor.AQUA;
		
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.weapon = new BruinSword (this.color);
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		
		this.setStat(MonsterStat.EXPERIENCE, 24.0);
	}
}
