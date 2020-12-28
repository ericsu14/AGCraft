package com.joojet.plugins.mobs.skills.attack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.spawning.FairSpawnController;

public abstract class AbstractAttackSkill extends AbstractSkill 
{
	/** Used to calculate fair spawn weights */
	protected FairSpawnController spawnWeight;
	
	public AbstractAttackSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(SkillPropetry.ATTACK, range, cooldown, maxUses, weight);
		this.spawnWeight = new FairSpawnController (this.getRange() / 2);
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
	 *  @param list List of living entities to be filtered.
	 *  @param caster Caster used for line of sight checks */
	public List <LivingEntity> filterByLineOfSight (List <LivingEntity> list, LivingEntity caster)
	{
		Object [] filtered = list.stream().filter(ent -> caster.hasLineOfSight(ent)).toArray();
		return Arrays.asList(Arrays.copyOf(filtered, filtered.length, LivingEntity[].class));
	}

}
