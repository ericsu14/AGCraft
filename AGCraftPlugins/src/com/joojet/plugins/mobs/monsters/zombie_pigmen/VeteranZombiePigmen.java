package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.ZombiePigmenHead;
import com.joojet.plugins.mobs.equipment.leggings.PigminTrousers;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.monsters.factions.EpicMob;


public class VeteranZombiePigmen extends EpicMob
{
	public VeteranZombiePigmen ()
	{
		super (MonsterType.VETERAN_ZOMBIE_PIGMAN);
		this.name = "Veteran Zombie Pigmen";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new ZombiePigmenHead (this.color);
		this.leggings = new PigminTrousers (this.color);
		this.weapon = new PigmanSword (this.color);
		this.setStat(MonsterStat.EXPERIENCE, 25.0);
	}
}
