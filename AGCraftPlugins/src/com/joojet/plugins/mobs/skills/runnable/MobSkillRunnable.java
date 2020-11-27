package com.joojet.plugins.mobs.skills.runnable;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.CustomSkillsListener;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.WeightedMobSkill;
import com.joojet.plugins.mobs.util.WeightedEntry;

public class MobSkillRunnable extends BukkitRunnable
{
	/** The living entity this instance is managing */
	protected LivingEntity caster;
	/** Mob equipment instance of the living entity */
	protected MobEquipment equipment;
	/** Contains the caster's active skills to be used */
	protected ArrayList <AbstractSkill> mobSkills;
	/** A reference to the custom skills listener used to handle the custom skills system */
	protected CustomSkillsListener customSkillsListener;
	/** A random number generator used to select random mob skills */
	protected Random rand;
	
	public MobSkillRunnable (LivingEntity caster, MobEquipment equipment, CustomSkillsListener customSkillsListener)
	{
		this.caster = caster;
		this.equipment = equipment;
		this.mobSkills = new ArrayList <AbstractSkill> ();
		this.rand = new Random ();
		this.customSkillsListener = customSkillsListener;
		
		if (this.equipment != null)
		{
			this.equipment.loadCustomSkills(this.mobSkills);
		}
	}
	
	@Override
	public void run() 
	{
		// Keep selecting a random skill to use if the caster is not dead
		if (this.caster != null && this.equipment != null && !caster.isDead())
		{
			AbstractSkill skill = this.getRandomSkill();
			if (skill != null)
			{
				this.customSkillsListener.useCustomSkill(caster, skill);
			}
		}
		else
		{
			this.cancel();
		}
	}
	
	/** Searches and returns a random skill that the monster can use, if any one exists.
	 *  This also updates the cooldown timer for all of the monster's skills. */
	protected AbstractSkill getRandomSkill ()
	{
		int minWeight = 0;
		int maxWeight = 0;
		ArrayList <WeightedMobSkill> skillList = new ArrayList <WeightedMobSkill> ();
		
		for (AbstractSkill skill : this.mobSkills)
		{
			if (skill.canUseSkill())
			{
				maxWeight = (minWeight + skill.getWeight()) - 1;
				skillList.add(new WeightedMobSkill (skill, minWeight, maxWeight));
				minWeight = maxWeight + 1;
			}
			skill.update(this.caster);
		}
		
		if (!skillList.isEmpty())
		{
			int roll = this.rand.nextInt(minWeight);
			return WeightedEntry.searchWeightedList(roll, skillList);
		}
		return null;
	}
	
}
