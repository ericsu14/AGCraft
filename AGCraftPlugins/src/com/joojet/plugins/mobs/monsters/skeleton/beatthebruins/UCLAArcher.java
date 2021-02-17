package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.weapons.BruinBow;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class UCLAArcher extends UCLAFaction
{
	public UCLAArcher ()
	{
		super (MonsterType.UCLA_ARCHER);
		this.name = "The " + generateUCLADisplayName("Archer");
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.10);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 7.0);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.20);
		this.color = ChatColor.AQUA;
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.weapon = new BruinBow ();
		
		this.setStat(MonsterStat.EXPERIENCE, 20.0);
	}
}
