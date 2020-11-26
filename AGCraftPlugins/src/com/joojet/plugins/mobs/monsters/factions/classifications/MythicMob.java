package com.joojet.plugins.mobs.monsters.factions.classifications;

import java.util.List;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class MythicMob extends MobEquipment 
{

	public MythicMob(MonsterType mobType) 
	{
		super(mobType);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.MYTHIC);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		// TODO Auto-generated method stub
		
	}
	
}
