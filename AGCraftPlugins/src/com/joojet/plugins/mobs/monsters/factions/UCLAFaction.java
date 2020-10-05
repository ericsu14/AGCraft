package com.joojet.plugins.mobs.monsters.factions;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;


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
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.RARE);
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
}
