package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.NMSSkillUser;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.visual.ChickenAura;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalPanic;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

public class SuperChicken extends USCFaction implements NMSSkillUser
{

	public SuperChicken() 
	{
		super(MonsterType.SUPER_CHICKEN);
		this.name = "Super Chicken";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.LEGENDARY);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 8.0);
		this.setStat(MonsterStat.HEALTH, 20.0);
		this.setStat(MonsterStat.BASE_ARMOR, 12.0);
		this.setStat(MonsterStat.BASE_KNOCKBACK_STRENGTH, 0.15);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new RageSkill (2, 60, 0.30));
		skills.add(new ChickenAura ());
	}

	@Override
	public void loadNMSSkills(EntityInsentient nmsMob, LivingEntity entity) 
	{
		nmsMob.goalSelector.a(1, new PathfinderGoalFloat((EntityCreature) nmsMob));
		nmsMob.goalSelector.a(4, new PathfinderGoalRandomStrollLand ((EntityCreature) nmsMob, 1.0D));
		nmsMob.goalSelector.a(4, new PathfinderGoalLeapAtTarget ((EntityCreature) nmsMob, 0.5F));
		nmsMob.goalSelector.a(4, new PathfinderGoalCustomMeleeAttack ((EntityCreature) nmsMob, this));
		
		// Attempts to remove the PANIC pathfinder goal from the chicken
		nmsMob.goalSelector.a(new PathfinderGoalPanic((EntityCreature) nmsMob, 1.4D));
	}

}
