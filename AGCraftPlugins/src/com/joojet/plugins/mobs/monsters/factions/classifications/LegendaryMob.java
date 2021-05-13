package com.joojet.plugins.mobs.monsters.factions.classifications;

import java.util.List;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.buff.SpeedBuffSkill;

public class LegendaryMob extends MobEquipment implements CustomSkillUser
{

	public LegendaryMob(MonsterType mobType) 
	{
		super(mobType);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.LEGENDARY);
		this.addTargetsToHitList(EntityType.PLAYER, EntityType.IRON_GOLEM, EntityType.SNOWMAN);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new AttackBuffSkill(0, 60, 20, 60, 8));
		skills.add(new ResistanceBuffSkill (0, 60, 20, 60, 8));
		skills.add(new SpeedBuffSkill (0, 60, 20, 60, 8));
	}

}
