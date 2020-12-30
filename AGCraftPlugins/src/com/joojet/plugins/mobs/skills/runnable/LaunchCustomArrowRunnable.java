package com.joojet.plugins.mobs.skills.runnable;

import org.bukkit.Location;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.joojet.plugins.mobs.equipment.offhand.TippedArrow;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;

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
	/** A reference to the plugin's monstertype interpreter, used to fetch custom monster data for this entity */
	protected MonsterTypeInterpreter mobInterpreter;
	/** The mob's custom tipped arrow that should be fired */
	private TippedArrow tippedArrow;
	
	public LaunchCustomArrowRunnable (LivingEntity caster, LivingEntity target, int ammo, double arrowDamage, MonsterTypeInterpreter mobInterpreter)
	{
		this.caster = caster;
		this.target = target;
		this.ammo = ammo;
		this.ammoCount = ammo;
		this.arrowDamage = arrowDamage;
		this.mobInterpreter = mobInterpreter;
		this.tippedArrow = this.getTippedArrowFromMob(this.caster);
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
			Arrow arrow = caster.getWorld().spawnArrow(arrowSpawnPoint, arrowDirection.normalize(), 1.2f, 2.0f);
			
			arrow.setDamage(this.arrowDamage);
			arrow.setCritical(true);
			arrow.setShooter(caster);
			arrow.setPickupStatus(PickupStatus.CREATIVE_ONLY);
			
			// Tipped arrow metadata
			if (this.tippedArrow != null)
			{
				this.tippedArrow.applyPotionDataToArrow(arrow);
			}
			
			--this.ammoCount;
		}
		else
		{
			this.cancel();
		}
	}
	
	/** Gets custom tipped-arrow data from a living entity with custom mob metadata
	 *  @param entity Entity we are extracting tipped-arrow data from */
	protected TippedArrow getTippedArrowFromMob (LivingEntity entity)
	{
		MobEquipment mobEquipment = this.mobInterpreter.getMobEquipmentFromEntity(entity);
		if (mobEquipment != null)
		{
			TippedArrow arrow = mobEquipment.getTippedArrow();
			// If the mob doesn't have a tipped arrow, check its offhand for any tipped arrow information
			if (arrow == null)
			{
				ItemStack [] mobEquips = mobEquipment.getEquipment();
				if (mobEquips[5] != null && mobEquips[5] instanceof TippedArrow)
				{
					arrow = (TippedArrow) mobEquips[5];
				}
			}
			
			return arrow;
		}
		return null;
	}
	
}
