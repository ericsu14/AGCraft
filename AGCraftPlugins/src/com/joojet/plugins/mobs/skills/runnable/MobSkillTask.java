package com.joojet.plugins.mobs.skills.runnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.CustomSkillsListener;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.TippedArrowSkill;
import com.joojet.plugins.mobs.skills.weightedentries.WeightedMobSkill;
import com.joojet.plugins.mobs.util.WeightedList;

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
	/** A reference to the monster type interpreter defined in the main plugin class */
	protected MonsterTypeInterpreter monsterInterpreter;
	/** A random number generator used to select random mob skills */
	protected Random rand;
	/** UUID of the entity this skill task is attached to */
	protected UUID casterUUID;
	
	public MobSkillTask (LivingEntity caster, MobEquipment equipment, CustomSkillsListener customSkillsListener, MonsterTypeInterpreter monsterTypeInterpreter)
	{
		this.caster = caster;
		this.equipment = equipment;
		this.mobSkills = new ArrayList <AbstractSkill> ();
		this.rand = new Random ();
		this.customSkillsListener = customSkillsListener;
		this.casterUUID = caster.getUniqueId();
		this.monsterInterpreter = monsterTypeInterpreter;
		this.loadSkills();
	}
	
	public void run() 
	{
		// Keep selecting a random skill to use if the caster is not dead
		AbstractSkill skill = this.getRandomSkill();
		if (skill != null)
		{
			this.customSkillsListener.useCustomSkill(caster, skill);
		}
	}
	
	/** Returns true if this mob skill task can be ran, which also checks if the entity is killed or not */
	public boolean canRunTask ()
	{
		return (this.caster != null && this.equipment != null && !caster.isDead() && !this.mobSkills.isEmpty());
	}
	
	/** Returns the UUID attached to the caster of this skill task */
	public UUID getCasterUUID ()
	{
		return this.casterUUID;
	}
	
	/** Returns the mobEquipment instance associated with the caster */
	public MobEquipment getMobEquipment ()
	{
		return this.equipment;
	}
	
	/** Searches and returns a random skill that the monster can use, if any one exists.
	 *  This also updates the cooldown timer for all of the monster's skills. */
	protected AbstractSkill getRandomSkill ()
	{
		WeightedList <WeightedMobSkill, AbstractSkill> skillList = new WeightedList <WeightedMobSkill, AbstractSkill> ();
		
		for (AbstractSkill skill : this.mobSkills)
		{
			if (skill.canUseSkill(this.caster))
			{
				skillList.addEntry(new WeightedMobSkill (skill, skill.getWeight()));
			}
			skill.update(this.caster);
		}
		
		if (!skillList.isEmpty())
		{
			return skillList.getRandomEntry();
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
			// Adds in the tipped arrow skill if the entity has a tipped arrow
			if (this.equipment.hasTippedArrow())
			{
				this.mobSkills.add(new TippedArrowSkill ());
			}
			
			// Loads in custom skills from the custom mob's MobFlags
			for (MobFlag flag : this.equipment.getMobFlags())
			{
				flag.loadCustomSkills(this.mobSkills);
			}
			
			// Loads in custom skills from the custom mob's MonsterStats
			for (MonsterStat stat : this.equipment.getStatContainer().keySet())
			{
				stat.loadCustomSkills(this.mobSkills);
			}
			
			if (this.equipment instanceof CustomSkillUser)
			{
				((CustomSkillUser) this.equipment).loadCustomSkills(this.mobSkills);
			}
			
			// Sets monster type interpreter instance for every skill in the skill list
			for (AbstractSkill skill : this.mobSkills)
			{
				skill.setMonsterTypeInterpreterInstance(this.monsterInterpreter);
			}
		}
	}
	
}
