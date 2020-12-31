package com.joojet.plugins.mobs.allies.golem;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.AnvilThrowSkill;
import com.joojet.plugins.mobs.skills.buff.SelfHealSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;

public class AdvancedGolem extends AlliedMob
{
	public AdvancedGolem ()
	{
		super (MonsterType.ADVANCED_GOLEM);
		
		this.name = "Advanced Golem";
		this.color = ChatColor.LIGHT_PURPLE;
		this.setStat(MonsterStat.HEALTH, 200.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.addTargetsToHitList(EntityType.CREEPER);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.SPEED,
				CustomPotionEffect.STRENGTH);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AnvilThrowSkill (20, 15, 16, 2.0f, 2));
		skills.add(new SelfHealSkill (3, 30, 4, 0.35));
		skills.add(new TeleportSkill (64, 60, Integer.MAX_VALUE, 2));
	}
}
