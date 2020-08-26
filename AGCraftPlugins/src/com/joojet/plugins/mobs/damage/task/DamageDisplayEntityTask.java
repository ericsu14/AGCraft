package com.joojet.plugins.mobs.damage.task;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.damage.DamageDisplayManager;

public class DamageDisplayEntityTask extends BukkitRunnable
{
	/** Armor stand instance this task is managing*/
	private ArmorStand armorStand;
	/** Instance of the Damage Display Manager */
	private DamageDisplayManager damageManager;
	
	/** Creates a new instance of this task
	 * 	@param armorStand - The armor stand instance this task is managing
	 *  @param damageManager - Damage manager instance that keeps track of all active armor stands */
	public DamageDisplayEntityTask (ArmorStand armorStand, DamageDisplayManager damageManager)
	{
		this.armorStand = armorStand;
		this.damageManager = damageManager;
	}
	
	/** Removes the armor stand from the world after a small period of time */
	@Override
	public void run() 
	{
		if (this.armorStand != null)
		{
			this.damageManager.removeDamageDisplayEntity(armorStand.getUniqueId());
		}
		this.cancel();
	}
}
