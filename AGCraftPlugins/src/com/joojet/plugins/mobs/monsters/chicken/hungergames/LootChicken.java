package com.joojet.plugins.mobs.monsters.chicken.hungergames;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.drops.WeightedDrop;
import com.joojet.plugins.mobs.drops.WeightedLootCrateDrop;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.interfaces.NMSSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.EvokerFangSkill;
import com.joojet.plugins.mobs.skills.passive.FearlessSkill;
import com.joojet.plugins.mobs.skills.visual.ChickenAura;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;

public class LootChicken extends MobEquipment implements NMSSkillUser, CustomSkillUser 
{

	public LootChicken() 
	{
		super(MonsterType.LOOT_CHICKEN);
		this.name = "Loot Chicken";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 8);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 3);
		this.addMobFlags(MobFlag.SHOW_NAME);
		
		this.addBiomes(Biome.THE_VOID);
		
		// Allows the chicken to hunt down players
		this.addTargetsToHitList(EntityType.PLAYER);
		
		this.addMonsterDrops(new WeightedLootCrateDrop (1, 1, 1, 
				new WeightedDrop (Material.BOW, 20),
				new WeightedDrop (Material.STONE_SWORD, 30),
				new WeightedDrop (Material.IRON_AXE, 2),
				new WeightedDrop (Material.IRON_SWORD, 8),
				new WeightedDrop (Material.IRON_CHESTPLATE, 5),
				new WeightedDrop (Material.IRON_LEGGINGS, 5),
				new WeightedDrop (Material.IRON_BOOTS, 5),
				new WeightedDrop (Material.IRON_HELMET, 5)
		));
		this.addMonsterDrops(new WeightedLootCrateDrop (1, 1, 3, 
				new WeightedDrop (Material.ARROW, 10),
				new WeightedDrop (Material.BAKED_POTATO, 25),
				new WeightedDrop (Material.COOKED_BEEF, 25),
				new WeightedDrop (Material.COOKED_CHICKEN, 20),
				new WeightedDrop (Material.COOKED_SALMON, 10),
				new WeightedDrop (Material.GOLDEN_APPLE, 1),
				new WeightedDrop (Material.ENDER_PEARL, 9)
		));
		
		this.addMonsterDrops(new WeightedLootCrateDrop (0.05, 1, 1, 
				new WeightedDrop (Material.DIAMOND, 100)
		));

	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new ChickenAura());
		skills.add(new FearlessSkill ());
		skills.add(new EvokerFangSkill (12, 10, Integer.MAX_VALUE, 4, 4));
	}

	@Override
	public void loadNMSSkills(PathfinderMob nmsMob, LivingEntity entity) 
	{
		nmsMob.goalSelector.addGoal(1, new FloatGoal(nmsMob));
		nmsMob.goalSelector.addGoal(4, new RandomStrollGoal (nmsMob, 1.0D));
		nmsMob.goalSelector.addGoal(4, new LeapAtTargetGoal (nmsMob, 0.5F));
		nmsMob.goalSelector.addGoal(4, new PathfinderGoalCustomMeleeAttack (nmsMob, this));
	}

}
