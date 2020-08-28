package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.FrostyFace;

public class Frosty extends AlliedMob
{
	public Frosty ()
	{
		super (MonsterType.FROSTY_THE_SNOWMAN);
		this.name = "Frosty the Snowman";
		this.color = ChatColor.AQUA;
		this.setStat(MonsterStat.HEALTH, 30.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, 
				CustomPotionEffect.WATER_BREATHING, 
				CustomPotionEffect.FIRE_RESISTANCE, 
				CustomPotionEffect.REGEN);
		
		// Chestplate
		this.helmet = new FrostyFace (this.color);
	}
}
