package com.joojet.plugins.mobs.allies.golem;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.ThePecks;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class JohnJae extends AlliedMob
{
	public JohnJae ()
	{
		super (MonsterType.JOHN_JAE);
		this.name = "John Jae";
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.setStat(MonsterStat.HEALTH, 250.0);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE_II, 
				CustomPotionEffect.REGEN, 
				CustomPotionEffect.STRENGTH_II, 
				CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.JUMP_BOOST, 
				CustomPotionEffect.SPEED);
		
		// Chestplate
		this.chestplate = new ThePecks (this.color);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		
	}
}
