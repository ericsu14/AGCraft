package com.joojet.plugins.mobs.allies.golem;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.ThePecks;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.AnvilDropSkill;
import com.joojet.plugins.mobs.skills.attack.AnvilThrowSkill;
import com.joojet.plugins.mobs.skills.buff.SelfHealSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;

public class JohnJae extends AlliedMob
{
	public JohnJae ()
	{
		super (MonsterType.JOHN_JAE);
		this.name = "John Jae";
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.setStat(MonsterStat.HEALTH, 250.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 35.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 8.0);
		this.setStat(MonsterStat.BASE_ARMOR, 6.0);
		this.addTargetsToHitList(EntityType.CREEPER);
		
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
		skills.add(new AnvilDropSkill (12, 12, 16, 4.0f, 1));
		skills.add(new AnvilThrowSkill (30, 25, 8, 2.0f, 3));
		skills.add(new SelfHealSkill (4, 60, 4, 0.45));
		skills.add(new TeleportSkill (64, 60, Integer.MAX_VALUE, 2));
	}
}
