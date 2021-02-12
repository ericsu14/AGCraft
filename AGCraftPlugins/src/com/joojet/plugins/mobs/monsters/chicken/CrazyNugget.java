package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedSpeedBuffSkill;

public class CrazyNugget extends AbstractChicken 
{
	public CrazyNugget ()
	{
		super (MonsterType.CRAZY_NUGGET);
		this.name = StringUtil.alternateTextColors("Crazy Nugget", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD);
		this.color = ChatColor.LIGHT_PURPLE;
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.COMMON);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 12.0);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.BASE_ARMOR, 8.0);
		this.setStat(MonsterStat.BASE_KNOCKBACK_STRENGTH, 0.15);
		this.setStat(MonsterStat.BASE_SPEED, 0.30);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		super.loadCustomSkills(skills);
		skills.add(new AlliedSpeedBuffSkill (0, 60, 16, 60, 8));
		skills.add(new ThundagaSkill (16, 8, Integer.MAX_VALUE, 2, 2.0f, 3, 40, 0.90));
	}

}
