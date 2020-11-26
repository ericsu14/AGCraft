package com.joojet.plugins.mobs.skills.runnable;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class MobSkillRunnable extends BukkitRunnable
{
	/** The living entity this instance is managing */
	protected LivingEntity caster;
	/** Mob equipment instance of the living entity */
	protected MobEquipment equipment;
	/** Contains the caster's active skills to be used */
	protected ArrayList <AbstractSkill> mobSkills;
	/** A random number generator used to select random mob skills */
	protected Random rand;
	
	public MobSkillRunnable (LivingEntity caster, MobEquipment equipment)
	{
		this.caster = caster;
		this.equipment = equipment;
		this.mobSkills = new ArrayList <AbstractSkill> ();
		this.rand = new Random ();
		
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
			
		}
	}
	

}
