package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedAttackBuffSkill;

public class ChickenFighter extends AbstractChicken 
{

	public ChickenFighter() 
	{
		super(MonsterType.CHICKEN_FIGHTER);
		this.name = StringUtil.alternateTextColors("The Fighter", TextPattern.WORD, 
				ChatColor.DARK_GRAY, ChatColor.YELLOW);
		this.color = org.bukkit.ChatColor.GOLD;
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 14.0);
		this.setStat(MonsterStat.BASE_ARMOR, 12.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 8.0);
		this.setStat(MonsterStat.BASE_KNOCKBACK_STRENGTH, 0.3);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		super.loadCustomSkills(skills);
		skills.add(new AlliedAttackBuffSkill (0, 60, 16, 60, 16));
	}

}
