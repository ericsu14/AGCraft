package com.joojet.plugins.mobs.monsters.chicken;

import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.NMSSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.SelfHealSkill;
import com.joojet.plugins.mobs.skills.passive.FearlessSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;
import com.joojet.plugins.mobs.skills.visual.ChickenAura;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

/** Represents an abstract chicken */
public abstract class AbstractChicken extends MobEquipment implements NMSSkillUser
{

	public AbstractChicken(MonsterType mobType) 
	{
		super(mobType);
		this.addFactions(Faction.CHICKEN_GANG);
		this.addRivalFactions(Faction.NETHER, Faction.DOOM_GUY, Faction.UCLA, Faction.PHANTOM);
		this.addBiomes(Biome.THE_VOID);
		
		// Allows the chicken to hunt down any hostile mob
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.GIANT, EntityType.HOGLIN,
				EntityType.VEX, EntityType.PIGLIN_BRUTE, EntityType.ZOGLIN, EntityType.ZOMBIE_VILLAGER);
		
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.IRON_GOLEM, EntityType.SNOWMAN, EntityType.WOLF,
				EntityType.VILLAGER, EntityType.WANDERING_TRADER);
		
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new ChickenAura());
		skills.add(new FearlessSkill ());
		skills.add(new SelfHealSkill (1, 30, 16, 0.20));
		skills.add(new TeleportSkill (128, 20, Integer.MAX_VALUE, 1));
	}

	@Override
	public void loadNMSSkills(EntityInsentient nmsMob, LivingEntity entity) 
	{
		nmsMob.goalSelector.a(1, new PathfinderGoalFloat((EntityCreature) nmsMob));
		nmsMob.goalSelector.a(4, new PathfinderGoalRandomStrollLand ((EntityCreature) nmsMob, 1.0D));
		nmsMob.goalSelector.a(4, new PathfinderGoalLeapAtTarget ((EntityCreature) nmsMob, 0.5F));
		nmsMob.goalSelector.a(4, new PathfinderGoalCustomMeleeAttack ((EntityCreature) nmsMob, this));
	}

}
