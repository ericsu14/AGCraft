package com.joojet.plugins.mobs.skills.runnable;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.CustomSkillsListener;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.WeightedMobSkill;
import com.joojet.plugins.mobs.skills.passive.ArrowDamageModifierSkill;
import com.joojet.plugins.mobs.skills.passive.CriticalShotSkill;
import com.joojet.plugins.mobs.skills.passive.PiercingBlowSkill;
import com.joojet.plugins.mobs.skills.passive.TippedArrowSkill;
import com.joojet.plugins.mobs.util.WeightedEntry;

public class MobSkillTask
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
	
	public MobSkillTask (LivingEntity caster, MobEquipment equipment, CustomSkillsListener customSkillsListener)
	{
		this.caster = caster;
		this.equipment = equipment;
		this.mobSkills = new ArrayList <AbstractSkill> ();
		this.rand = new Random ();
		this.customSkillsListener = customSkillsListener;
		this.loadSkills();
	}
	
	public void run() 
	{
		// Keep selecting a random skill to use if the caster is not dead
		if (this.caster != null && this.equipment != null && !caster.isDead() && !this.mobSkills.isEmpty())
		{
			AbstractSkill skill = this.getRandomSkill();
			if (skill != null)
			{
				this.customSkillsListener.useCustomSkill(caster, skill);
			}
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
			if (skill.canUseSkill(this.caster))
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
	
	/** Returns the list of skills attached to this task */
	public ArrayList <AbstractSkill> getSkillList ()
	{
		return this.mobSkills;
	}
	
	/** Returns the total number of skills that are attached to the custom monster */
	public int getSkillSize()
	{
		return this.mobSkills.size();
	}
	
	/** Initializes the caster's list of skills */
	protected void loadSkills ()
	{
		if (this.equipment != null)
		{
			// Adds in the arrow's base damage modifier skill if the entity's ARROW_BASE_DAMAGE is set
			if (this.equipment.containsStat(MonsterStat.BASE_ARROW_DAMAGE))
			{
				this.mobSkills.add(new ArrowDamageModifierSkill ());
			}
			// Adds in critical shot skill if the entity's ARROW_CRITICAL_CHANCE is enabled
			if (this.equipment.containsStat(MonsterStat.ARROW_CRITICAL_CHANCE))
			{
				this.mobSkills.add(new CriticalShotSkill ());
			}
			// Adds in the piercing blow skill if the entity's ARROW_PIERCING_CHANCE is enabled
			if (this.equipment.containsStat(MonsterStat.ARROW_PIERCING_CHANCE))
			{
				this.mobSkills.add(new PiercingBlowSkill ());
			}
			// Adds in the tipped arrow skill if the entity has a tipped arrow
			if (this.equipment.hasTippedArrow())
			{
				this.mobSkills.add(new TippedArrowSkill ());
			}
			this.equipment.loadCustomSkills(this.mobSkills);
		}
	}
	
}
