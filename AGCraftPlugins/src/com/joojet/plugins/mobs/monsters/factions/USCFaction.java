package com.joojet.plugins.mobs.monsters.factions;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public abstract class USCFaction extends MobEquipment
{
	public static String USC_TEXT = StringUtil.alternateTextColors("USC", TextPattern.CHARACTER, ChatColor.RED,
			ChatColor.GOLD);
	
	public USCFaction (MonsterType type)
	{
		super (type);
		this.addFactions(Faction.USC);
		this.addRivalFactions(Faction.UCLA, Faction.PHANTOM, Faction.DOOM_GUY);
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.GIANT, EntityType.HOGLIN,
				EntityType.VEX, EntityType.PIGLIN_BRUTE);
		
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.CAT,
				EntityType.IRON_GOLEM, EntityType.SNOWMAN, EntityType.DOLPHIN, EntityType.VILLAGER,
				EntityType.WANDERING_TRADER, EntityType.CREEPER, EntityType.PANDA);
	}
	
	/** A util function that generates a USC themed display name
	 *  that appends the USC text defined above at the beginning 
	 *  of the String */
	public static String generateUSCDisplayName (String str)
	{
		StringBuilder result = new StringBuilder (USC_TEXT);
		result.append(" ");
		result.append(StringUtil.alternateTextColors(str, TextPattern.WORD, 
				ChatColor.GOLD, ChatColor.RED));
		return result.toString();
	}
}
