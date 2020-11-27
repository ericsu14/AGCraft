package com.joojet.plugins.mobs.monsters.factions;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;


public abstract class UCLAFaction extends MobEquipment
{
	public static String UCLA_TEXT = StringUtil.alternateTextColors("UCLA", TextPattern.CHARACTER, ChatColor.AQUA,
			ChatColor.GOLD);
			
	public UCLAFaction (MonsterType type)
	{
		super (type);
		this.addFactions(Faction.UCLA);
		this.addRivalFactions(Faction.USC, Faction.PHANTOM, Faction.DOOM_GUY);
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON,
				EntityType.PLAYER, EntityType.PHANTOM, EntityType.WITHER_SKELETON, 
				EntityType.SPIDER, EntityType.IRON_GOLEM, EntityType.HOGLIN);
		this.addEntitiesToIgnoreList(EntityType.CREEPER);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.EPIC);
	}
	
	/** A util function that generates a UCLA themed display name
	 *  that appends the UCLA text defined above at the beginning 
	 *  of the String */
	public static String generateUCLADisplayName (String str)
	{
		StringBuilder result = new StringBuilder (UCLA_TEXT);
		result.append(" ");
		result.append(StringUtil.alternateTextColors(str, TextPattern.WORD, 
				ChatColor.AQUA, ChatColor.GOLD));
		return result.toString();
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AttackBuffSkill(1, 60, 15, 60, 8));
		skills.add(new ResistanceBuffSkill (1, 45, 15, 60, 8));
	}
}
