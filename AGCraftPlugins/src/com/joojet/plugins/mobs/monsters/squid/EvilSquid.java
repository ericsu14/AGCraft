package com.joojet.plugins.mobs.monsters.squid;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.NMSSkillUser;
import com.joojet.plugins.mobs.monsters.factions.classifications.RareMob;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.LazerBeamAttack;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStroll;

public class EvilSquid extends RareMob implements NMSSkillUser
{
	public EvilSquid() 
	{
		super(MonsterType.EVIL_SQUID);
		this.name = "Evil Squid";
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 8.0);
		this.setStat(MonsterStat.BASE_ARMOR, 12.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 4.0);
		
		this.addTargetsToHitList(EntityType.PLAYER, EntityType.DROWNED, EntityType.GUARDIAN,
				EntityType.ELDER_GUARDIAN, EntityType.ZOMBIE, EntityType.COD, EntityType.SALMON,
				EntityType.TROPICAL_FISH, EntityType.TURTLE);
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new LazerBeamAttack (16, 12, Integer.MAX_VALUE, 4, 80));
	}
	
	@Override
	public void loadNMSSkills(EntityInsentient nmsMob, LivingEntity entity) 
	{
		nmsMob.goalSelector.a(4, new PathfinderGoalLeapAtTarget ((EntityCreature) nmsMob, 0.5F));
		nmsMob.goalSelector.a(20, new PathfinderGoalCustomMeleeAttack ((EntityCreature) nmsMob, this));
		nmsMob.goalSelector.a(6, new PathfinderGoalRandomStroll ((EntityCreature) nmsMob, 1.0D, 80));
	}
	
}
