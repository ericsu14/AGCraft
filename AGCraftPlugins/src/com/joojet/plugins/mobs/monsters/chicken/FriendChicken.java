package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.visual.FireAura;

public class FriendChicken extends AbstractChicken 
{
	public FriendChicken() 
	{
		super(MonsterType.FRIEND_CHICKEN);
		this.name = StringUtil.alternateTextColors("друг", TextPattern.CHARACTER, ChatColor.RED, ChatColor.DARK_RED, 
				ChatColor.DARK_GRAY, ChatColor.BLACK);
		this.color = ChatColor.DARK_RED;
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 22.0);
		this.setStat(MonsterStat.HEALTH, 8.0);
		this.setStat(MonsterStat.BASE_ARMOR, 20.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 20.0);
		this.setStat(MonsterStat.BASE_SPEED, 0.15);
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		super.loadCustomSkills(skills);
		skills.add(new RageSkill (3, 60, 0.30));
		skills.add(new FireAura ());
	}
}
