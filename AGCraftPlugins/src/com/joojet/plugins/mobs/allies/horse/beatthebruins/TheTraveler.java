package com.joojet.plugins.mobs.allies.horse.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class TheTraveler extends USCFaction 
{
	public TheTraveler ()
	{
		super (MonsterType.THE_TRAVELER);
		this.addMobFlags(MobFlag.ENABLE_PERSISTENCE_UPON_RIDING);
		this.color = ChatColor.RED;
		this.name = "The"
				+ ChatColor.GOLD + " Traveler";
		this.addBiomes(Biome.THE_VOID);
		
		this.boots = new LetItGo (this.color);
		
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.setStat(MonsterStat.HORSE_COLOR, Color.WHITE.ordinal());
		this.setStat(MonsterStat.HORSE_STYLE, Style.WHITEFIELD.ordinal());
		this.setStat(MonsterStat.BASE_SPEED, 0.40);
		this.setStat(MonsterStat.HORSE_JUMP_STRENGTH, 1.0);
	}
}
