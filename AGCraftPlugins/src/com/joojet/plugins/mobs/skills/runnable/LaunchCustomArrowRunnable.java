package com.joojet.plugins.mobs.skills.runnable;

import org.bukkit.Location;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class LaunchCustomArrowRunnable extends BukkitRunnable {
	
	/** Total amount of arrows to be launched */
	protected int ammo;
	/** Skill caster */
	protected LivingEntity caster;
	/** Target */
	protected LivingEntity target;
	/** Internal ammo counter */
	private int ammoCount;
	/** Total amount of damage dealt by the arrow */
	private double arrowDamage;
	
	public LaunchCustomArrowRunnable (LivingEntity caster, LivingEntity target, int ammo, double arrowDamage)
	{
		this.caster = caster;
		this.target = target;
		this.ammo = ammo;
		this.ammoCount = ammo;
		this.arrowDamage = arrowDamage;
	}
	
	@Override
	public void run() 
	{
		if (ammoCount > 0 && !caster.isDead() && !target.isDead())
		{
			// Calculate the arrow's spawn location, which is going to be in front of the caster
			// based on its forward vector
			
			Location arrowSpawnPoint = caster.getEyeLocation().clone();
			arrowSpawnPoint.add(arrowSpawnPoint.getDirection());
			
			Vector arrowDirection = target.getEyeLocation().toVector();
			arrowDirection.subtract(caster.getEyeLocation().toVector());
			Arrow arrow = caster.getWorld().spawnArrow(arrowSpawnPoint, arrowDirection.normalize(), 1.2f, 1.0f);
			
			arrow.setDamage(this.arrowDamage);
			arrow.setCritical(true);
			arrow.setShooter(caster);
			arrow.setPickupStatus(PickupStatus.CREATIVE_ONLY);
			--this.ammoCount;
		}
		else
		{
			this.cancel();
		}
	}

}
