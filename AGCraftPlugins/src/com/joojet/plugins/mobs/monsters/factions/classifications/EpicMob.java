package com.joojet.plugins.mobs.monsters.factions.classifications;

import java.util.List;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class EpicMob extends MobEquipment 
{
	public EpicMob(MonsterType mobType) 
	{
		super(mobType);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.EPIC);
		this.addTargetsToHitList(EntityType.IRON_GOLEM, EntityType.SNOWMAN);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{

	}
	
}
