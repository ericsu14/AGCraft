package com.joojet.plugins.mobs.monsters.factions.classifications;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class RareMob extends MobEquipment 
{
	public RareMob(MonsterType mobType) 
	{
		super(mobType);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.RARE);
		this.addTargetsToHitList(EntityType.IRON_GOLEM, EntityType.SNOWMAN);
	}

}
