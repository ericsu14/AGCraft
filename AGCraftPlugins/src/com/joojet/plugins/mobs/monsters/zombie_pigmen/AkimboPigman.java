package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.offhand.PigmanDagger;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.monsters.factions.LegendaryMob;


public class AkimboPigman extends LegendaryMob 
{
	public AkimboPigman ()
	{
		super (MonsterType.AKIMBO_PIGMAN);
		this.name = "Akimbo Pigman";
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new DarkNetheriteHelmet (this.color);
		this.weapon = new PigmanSword (this.color);
		this.offhand = new PigmanDagger (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 40.0);
	}
}
