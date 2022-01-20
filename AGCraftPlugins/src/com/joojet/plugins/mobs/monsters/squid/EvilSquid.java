package com.joojet.plugins.mobs.monsters.squid;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.interfaces.NMSSkillUser;
import com.joojet.plugins.mobs.monsters.factions.classifications.RareMob;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.visual.SquidPower;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;

public class EvilSquid extends RareMob implements NMSSkillUser, CustomSkillUser
{
	public EvilSquid() 
	{
		super(MonsterType.EVIL_SQUID);
		this.name = "Evil Squid";
		this.addBiomes(Biome.DEEP_COLD_OCEAN, Biome.DEEP_FROZEN_OCEAN, Biome.DEEP_OCEAN);
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 8.0);
		this.setStat(MonsterStat.BASE_ARMOR, 12.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 4.0);
		
		this.addTargetsToHitList(EntityType.PLAYER, EntityType.DROWNED, EntityType.GUARDIAN,
				EntityType.ELDER_GUARDIAN, EntityType.ZOMBIE, EntityType.COD, EntityType.SALMON,
				EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.SQUID, EntityType.GLOW_SQUID);
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new SquidPower (0.10));
	}
	
	@Override
	public void loadNMSSkills(PathfinderMob nmsMob, LivingEntity entity) 
	{
		nmsMob.goalSelector.addGoal(4, new LeapAtTargetGoal (nmsMob, 0.5F));
		nmsMob.goalSelector.addGoal(20, new PathfinderGoalCustomMeleeAttack (nmsMob, this));
		nmsMob.goalSelector.addGoal(6, new RandomStrollGoal (nmsMob, 1.0D, 80));
	}
	
}
