package com.joojet.plugins.mobs.monsters.factions;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.potionthrow.TrojanPotionThrow;
import com.joojet.plugins.mobs.skills.passive.USCTracerSkill;

public abstract class USCFaction extends MobEquipment implements CustomSkillUser
{
	public static String USC_TEXT = StringUtil.alternateTextColors("USC", TextPattern.CHARACTER, ChatColor.RED,
			ChatColor.GOLD);
	
	public USCFaction (MonsterType type)
	{
		super (type);
		this.addFactions(Faction.USC);
		this.addRivalFactions(Faction.UCLA, Faction.PHANTOM, Faction.DOOM_GUY,
				Faction.MR_JOHNSON, Faction.NETHER);
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.GIANT, EntityType.HOGLIN,
				EntityType.VEX, EntityType.PIGLIN_BRUTE, EntityType.ZOGLIN, EntityType.ZOMBIE_VILLAGER,
				EntityType.SHULKER);
		
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.CAT,
				EntityType.IRON_GOLEM, EntityType.SNOWMAN, EntityType.DOLPHIN, EntityType.VILLAGER,
				EntityType.WANDERING_TRADER, EntityType.CREEPER, EntityType.PANDA);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.LEGENDARY);
		this.addMobFlags(MobFlag.DISABLE_PICK_UP_ITEMS);
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
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new TrojanPotionThrow(16, 30, 3, 1));
		skills.add(new USCTracerSkill ());
	}
}
