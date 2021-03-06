package com.joojet.plugins.mobs.monsters.factions.classifications;

import java.util.List;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.utility.AggressiveTeleportSkill;

public class MythicMob extends MobEquipment implements CustomSkillUser
{

	public MythicMob(MonsterType mobType) 
	{
		super(mobType);
		this.addMobFlags(MobFlag.DISABLE_PICK_UP_ITEMS);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.MYTHIC);
		this.addTargetsToHitList(EntityType.IRON_GOLEM, EntityType.SNOWMAN);
		this.setStat(MonsterStat.SPAWN_LIMIT, 2);
		this.setStat(MonsterStat.SPAWN_LIMIT_COOLDOWN, 300);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		// If a mythic mob does not have any predefined skill, automatically default to using
		// the rage and aggressive teleport skills
		skills.add(new RageSkill (0, 15, 0.30));
		skills.add(new AggressiveTeleportSkill (156, 10, Integer.MAX_VALUE, 2));
	}
	
}
