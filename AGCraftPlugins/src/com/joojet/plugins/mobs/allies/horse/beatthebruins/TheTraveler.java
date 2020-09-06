package com.joojet.plugins.mobs.allies.horse.beatthebruins;

import org.bukkit.block.Biome;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class TheTraveler extends MobEquipment 
{
	public TheTraveler ()
	{
		super (MonsterType.THE_TRAVELER);
		this.name = "The Traveler";
		this.addBiomes(Biome.THE_VOID);
		
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.setStat(MonsterStat.HORSE_COLOR, Color.WHITE.ordinal());
		this.setStat(MonsterStat.HORSE_STYLE, Style.WHITEFIELD.ordinal());
		this.addMobFlags(MobFlag.SHOW_NAME);
	}
}
