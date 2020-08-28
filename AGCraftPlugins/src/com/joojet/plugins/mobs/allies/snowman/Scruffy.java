package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.head.ScruffyFace;

public class Scruffy extends AlliedMob
{
	public Scruffy ()
	{
		super (MonsterType.SCRUFFY);
		this.name = "Scruffy";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.WATER_BREATHING,
				CustomPotionEffect.FIRE_RESISTANCE, CustomPotionEffect.REGEN, CustomPotionEffect.SPEED);
		
		// Chestplate
		this.helmet = new ScruffyFace (this.color);
		
		// Boots
		this.boots = new LetItGo (this.color);
	}
		
}
