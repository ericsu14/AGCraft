package com.joojet.plugins.mobs.monsters.phantom.julyfourth;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.JulyFourthTracerSkill;
import com.joojet.plugins.mobs.skills.visual.FireworkExplosionDeath;

public class FireworkPhantom extends MobEquipment implements CustomSkillUser
{
	public FireworkPhantom ()
	{
		super (MonsterType.FIREWORK_PHANTOM);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 6.0);
		this.addMobFlags(MobFlag.ON_FIRE);
		this.name = StringUtil.alternateTextColors("Shoot Me", TextPattern.WORD, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		
		this.addTargetsToHitList(EntityType.SKELETON, EntityType.ZOMBIE, EntityType.SPIDER, EntityType.CAVE_SPIDER,
				EntityType.WITCH, EntityType.CREEPER, EntityType.CAVE_SPIDER, EntityType.ZOMBIFIED_PIGLIN,
				EntityType.HUSK, EntityType.STRAY, EntityType.ENDERMAN, EntityType.GIANT, EntityType.PILLAGER,
				EntityType.VEX, EntityType.VINDICATOR, EntityType.EVOKER, EntityType.GUARDIAN
				,EntityType.DROWNED, EntityType.RAVAGER);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new FireworkExplosionDeath ());
		skills.add(new JulyFourthTracerSkill ());
	}
}
