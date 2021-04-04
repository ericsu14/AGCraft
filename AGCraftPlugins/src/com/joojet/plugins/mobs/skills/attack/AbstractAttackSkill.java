package com.joojet.plugins.mobs.skills.attack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

public abstract class AbstractAttackSkill extends AbstractSkill 
{
	public AbstractAttackSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(SkillPropetry.ATTACK, range, cooldown, maxUses, weight);
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
	
	/** Filters a list of entities by removing any entity that is not within the caster's line of sight.
	 *  @param stream Stream of living entities to be filtered.
	 *  @param caster Caster used for line of sight checks */
	public Stream <LivingEntity> filterByLineOfSight (Stream <LivingEntity> stream, LivingEntity caster)
	{
		return stream.filter(ent -> caster.hasLineOfSight(ent));
	}
	
	/** Returns true if the caster is wielding a bow */
	protected boolean hasBow (LivingEntity caster)
	{
		ItemStack hand = caster.getEquipment().getItemInMainHand();
		return (hand != null && (hand.getType() == Material.BOW || hand.getType() == Material.CROSSBOW));
	}

}
