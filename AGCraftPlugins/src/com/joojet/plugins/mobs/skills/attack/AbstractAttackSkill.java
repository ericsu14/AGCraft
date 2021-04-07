package com.joojet.plugins.mobs.skills.attack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

public abstract class AbstractAttackSkill extends AbstractSkill 
{
	/** A set of undead monsters */
	public EnumSet <EntityType> undeadMonsters;
	
	public AbstractAttackSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(SkillPropetry.ATTACK, range, cooldown, maxUses, weight);
		undeadMonsters = EnumSet.of(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, 
				EntityType.ZOMBIFIED_PIGLIN, EntityType.SKELETON, EntityType.WITHER_SKELETON,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.DROWNED, EntityType.STRAY, EntityType.HUSK, EntityType.PHANTOM,
				EntityType.WITHER, EntityType.ZOGLIN, EntityType.SKELETON_HORSE, EntityType.ZOMBIE_HORSE);
	}
	
	/** Randomly selects a set of entities from a list of LivingEntities.
	 *  The amount of entities selected is configurable based on the input, amount..
	 * 	@param entities - A list of entities to choose from
	 *  @param amount - Total amount of random entities selected */
	public List <LivingEntity> selectRandomEntities (List <LivingEntity> entities, int amount)
	{
		int n = entities.size();
		ArrayList <LivingEntity> result = new ArrayList <LivingEntity> ();
		
		// Returns an empty array if the amount is invalid or bigger than the list itself.
		if (amount <= 0)
		{
			return result;
		}
		
		HashSet <Integer> capturedIndicies = new HashSet <Integer> ();
		
		int currAmount = amount;
		while (currAmount > 0 && result.size() < n)
		{
			int randIndex = this.random.nextInt(n);
			if (!capturedIndicies.contains(randIndex))
			{
				capturedIndicies.add(randIndex);
				result.add(entities.get(randIndex));
				--currAmount;
			}
		}
		
		return result;
	}
	
	/** Returns true if the caster is wielding a bow */
	protected boolean hasBow (LivingEntity caster)
	{
		ItemStack hand = caster.getEquipment().getItemInMainHand();
		return (hand != null && (hand.getType() == Material.BOW || hand.getType() == Material.CROSSBOW));
	}

}
