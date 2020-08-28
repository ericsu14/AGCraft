package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;

public class AdvancedGolem extends AlliedMob
{
	public AdvancedGolem ()
	{
		super (MonsterType.ADVANCED_GOLEM);
		
		this.name = "Advanced Golem";
		this.color = ChatColor.LIGHT_PURPLE;
		this.setStat(MonsterStat.HEALTH, 200.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.SPEED,
				CustomPotionEffect.STRENGTH);
	}
}
