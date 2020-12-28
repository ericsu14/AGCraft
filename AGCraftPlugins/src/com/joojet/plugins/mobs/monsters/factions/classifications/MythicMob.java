package com.joojet.plugins.mobs.monsters.factions.classifications;

import java.util.List;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.utility.AgressiveTeleportSkill;

public class MythicMob extends MobEquipment 
{

	public MythicMob(MonsterType mobType) 
	{
		super(mobType);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.MYTHIC);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		// If a mythic mob does not have any predefined skill, automatically default to using
		// the rage skill
		skills.add(new RageSkill (1, 90, 0.30));
		skills.add(new AgressiveTeleportSkill (256, 20, Integer.MAX_VALUE, 2));
	}
	
}
