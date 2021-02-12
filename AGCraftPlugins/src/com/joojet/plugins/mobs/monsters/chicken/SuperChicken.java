package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.SuperFeather;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.summon.SummonChickenGang;

public class SuperChicken extends AbstractChicken
{
	public SuperChicken() 
	{
		super(MonsterType.SUPER_CHICKEN);
		this.setDropRates(0.0f, 0.01f, 0.0f, 0.0f, 0.0f, 0.0f);
		this.name = "Super Chicken";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.LEGENDARY);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 15.0);
		this.setStat(MonsterStat.HEALTH, 30.0);
		this.setStat(MonsterStat.BASE_ARMOR, 12.0);
		this.setStat(MonsterStat.BASE_KNOCKBACK_STRENGTH, 0.15);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.addPotionEffect(CustomPotionEffect.REGEN);
		this.chestplate = new SuperFeather ();
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		super.loadCustomSkills(skills);
		skills.add(new RageSkill (2, 60, 0.30));
		skills.add(new SummonChickenGang (16, 120, Integer.MAX_VALUE, 16, 5));
	}
}
