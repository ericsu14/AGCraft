package com.joojet.plugins.mobs.damage;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.damage.entities.DamageDisplayEntity;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class DamageDisplayManager 
{
	/** Keeps track of active damage displays */
	protected ConcurrentHashMap <UUID, ArmorStand> activeDisplayEntities;
	/** A set of Block Types a player cannot be on while detecting critical hits */
	protected HashSet <Material> invalidBlocks;
	
	public DamageDisplayManager ()
	{
		this.activeDisplayEntities = new ConcurrentHashMap <UUID, ArmorStand> ();
		
		this.invalidBlocks = new HashSet <Material> ();
		this.invalidBlocks.add(Material.LADDER);
		this.invalidBlocks.add(Material.VINE);
		this.invalidBlocks.add(Material.WATER);
		this.invalidBlocks.add(Material.LAVA);
	}
	
	public void createDamageDisplayonEntity (EntityDamageByEntityEvent event)
	{
		if (event.getEntity() == null || !(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		LivingEntity damaged = (LivingEntity) event.getEntity();
		ArmorStand damageDisplayEntity = (ArmorStand) damaged.getWorld().spawnEntity(damaged.getLocation(), EntityType.ARMOR_STAND);
		boolean criticalHit = false;
		if (event.getDamager() instanceof Player)
		{
			criticalHit = this.checkCriticalHit((Player) event.getDamager());
		}
		
		damageDisplayEntity.setInvulnerable(true);
		damageDisplayEntity.setVisible(false);
		EquipmentTools.equipEntity(damageDisplayEntity, new DamageDisplayEntity (event.getFinalDamage(), criticalHit));
		this.activeDisplayEntities.put(damageDisplayEntity.getUniqueId(), damageDisplayEntity);
	}
	
	/** Returns true if a player has performed a critical hit.
	 *  This is done according to the conditions listed in
	 *     - https://minecraft.gamepedia.com/Damage
	 * 	 @param player - Player being checked */
	public boolean checkCriticalHit (Player player)
	{
		boolean result = true;
		// The player is falling
		result &= player.getFallDistance() > 0.0F;
		// The player must not be on the ground
		// Or not currently in any ladder or fluid type
		Material currentBlockType = player.getLocation().getBlock().getType();
		result &= (currentBlockType == Material.AIR
				|| !this.invalidBlocks.contains(currentBlockType));
		// The player must not be affected by blindness
		result &= player.hasPotionEffect(PotionEffectType.BLINDNESS);
		// The player must not be riding an entity
		result &= !player.isInsideVehicle();
		// The player must not be sprinting
		result &= !player.isSprinting();
		// The player's cooldown must be over 84.8% ready 
		result &= player.getAttackCooldown() >= 0.848;
		return result;
	}
}
