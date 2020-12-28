package com.joojet.plugins.mobs.allies.snowman;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.FrostyFace;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.SelfHealSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;

public class Frosty extends AlliedMob
{
	public Frosty ()
	{
		super (MonsterType.FROSTY_THE_SNOWMAN);
		this.name = "Frosty the Snowman";
		this.color = ChatColor.AQUA;
		this.setStat(MonsterStat.HEALTH, 30.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.addEntitiesToIgnoreList(EntityType.CREEPER);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, 
				CustomPotionEffect.WATER_BREATHING, 
				CustomPotionEffect.FIRE_RESISTANCE, 
				CustomPotionEffect.REGEN);
		
		// Chestplate
		this.helmet = new FrostyFace (this.color);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new ThundagaSkill (20, 14, Integer.MAX_VALUE, 12, 1.0F, 3, 20, 1.00));
		skills.add(new SelfHealSkill (1, 30, 1, 0.40));
		skills.add(new TeleportSkill (64, 60, Integer.MAX_VALUE, 1));
	}
}
