package com.joojet.plugins.mobs.damage;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.damage.entities.DamageDisplayEntity;
import com.joojet.plugins.mobs.damage.enums.DamageType;
import com.joojet.plugins.mobs.damage.task.DamageDisplayEntityTask;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class DamageDisplayManager 
{
	/** Keeps track of active damage displays */
	protected ConcurrentHashMap <UUID, ArmorStand> activeDisplayEntities;
	/** A set of Block Types a player cannot be on while detecting critical hits */
	protected HashSet <Material> invalidBlocks;
	/** Used to calculate a random offset */
	protected Random rand;
	
	public DamageDisplayManager ()
	{
		this.activeDisplayEntities = new ConcurrentHashMap <UUID, ArmorStand> ();
		
		this.invalidBlocks = new HashSet <Material> ();
		this.invalidBlocks.add(Material.LADDER);
		this.invalidBlocks.add(Material.VINE);
		this.invalidBlocks.add(Material.WATER);
		this.invalidBlocks.add(Material.LAVA);
		this.rand = new Random ();
	}
	
	
	
	/** Creates an invisible armor stand displaying the final damage dealt to an entity after an attack */
	public void createDamageDisplayonEntity (Entity entity, DamageType damageType, double health)
	{
		if (entity == null || health <= 0.0)
		{
			return;
		}
		
		BoundingBox entityBox = entity.getBoundingBox();
		Location entityLocation;
		// Spawns this damage information entity at an offset slightly outside of the entity's hitbox.
		double xRandomOffset = this.generateRandomNumber(-0.3, 0.3);
		double yRandomOffset = this.generateRandomNumber(0.1, 0.3);
		double zRandomOffset = this.generateRandomNumber(-0.3, 0.3);
		
		if (damageType == DamageType.NORMAL || damageType == DamageType.CRITICAL
				|| damageType == DamageType.PROJECTILE || damageType == DamageType.ALLIED)
		{
			entityLocation = new Location (entity.getWorld(), entityBox.getMinX() + xRandomOffset, entityBox.getMaxY() + this.generateRandomNumber(-0.1, 0.0), 
					entityBox.getMaxZ() + zRandomOffset);
		}
		else
		{
			entityLocation = new Location (entity.getWorld(), entityBox.getMinX() + xRandomOffset, entityBox.getMaxY() + yRandomOffset, 
					entityBox.getMinZ() + zRandomOffset);
		}
		ArmorStand damageDisplayEntity = (ArmorStand) entity.getWorld().spawn(entityLocation, ArmorStand.class, armorStand -> {
			armorStand.setInvulnerable(true);
			armorStand.setVisible(false);
			armorStand.setMarker(true);
			EquipmentTools.equipEntity(armorStand, new DamageDisplayEntity (health, damageType));
		});
		
		this.activeDisplayEntities.put(damageDisplayEntity.getUniqueId(), damageDisplayEntity);
		new DamageDisplayEntityTask (damageDisplayEntity, this).runTaskLaterAsynchronously(AGCraftPlugin.plugin, 20);
	}
	
	/** Removes a damage display entity fron the server */
	public void removeDamageDisplayEntity (UUID entityUUID)
	{
		if (this.activeDisplayEntities.containsKey(entityUUID))
		{
			this.activeDisplayEntities.get(entityUUID).remove();
			this.activeDisplayEntities.remove(entityUUID);
		}
	}
	
	/** Removes all active damage display entities from the game */
	public void cleanup ()
	{
		for (ArmorStand armorstand : this.activeDisplayEntities.values())
		{
			if (armorstand != null)
			{
				armorstand.remove();
			}
		}
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
		result &= !player.hasPotionEffect(PotionEffectType.BLINDNESS);
		// The player must not be riding an entity
		result &= !player.isInsideVehicle();
		// The player must not be sprinting
		result &= !player.isSprinting();
		// The player's cooldown must be over 84.8% ready 
		result &= player.getAttackCooldown() >= 0.848;
		return result;
	}
	
	/** Generates a random double between a certain range
	 * 	@param min - Min. range of random number generation
	 * 	@param max - Max. range of random number generation */
	private double generateRandomNumber (double min, double max)
	{
		return min + (max - min) * this.rand.nextDouble();
	}
}
