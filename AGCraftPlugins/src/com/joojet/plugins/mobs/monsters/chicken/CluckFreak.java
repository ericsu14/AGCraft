package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.EvokerFangSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedResistanceBuffSkill;

public class CluckFreak extends AbstractChicken
{
	public CluckFreak ()
	{
		super (MonsterType.CLUCK_FREAK);
		
		this.name = "Cluck Freak";
		this.color = ChatColor.LIGHT_PURPLE;
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.COMMON);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 10.0);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.BASE_ARMOR, 4.0);
		this.setStat(MonsterStat.BASE_KNOCKBACK_STRENGTH, 0.15);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		super.loadCustomSkills(skills);
		skills.add(new AlliedResistanceBuffSkill (0, 60, 16, 60, 16));
		skills.add(new EvokerFangSkill (12, 10, Integer.MAX_VALUE, 4, 8));
	}
}
