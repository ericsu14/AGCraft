package com.joojet.plugins.mobs.skills.passive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

/** Allows snowball projectiles fired by the skillcaster to actually deal damage and slow enemies down
 *  for several seconds */
public class IcySnowballSkill extends AbstractPassiveSkill implements PassiveProjectile, PassiveAttack
{
	/** Tracks the UUID of all snowballs fired by this entity*/
	protected HashMap <UUID, Projectile> snowballs;
	/** Chance of spawning an Icy snowball */
	protected double snowballChance;
	/** Base damage of thrown snowballs */
	protected double baseSnowballDamage;
	/** Duration of the slowness effect from thrown snowballs */
	protected final int slowDuration = 100;
	/** Base damage multiplier used to add a random offset in the damage calculations */
	protected final double damageMultiplierOffset = 0.20;
	
	/** Allows the custom monster to throw damaging snowballs that inflict slowness on its targets
	 *  @param snowballChance Chance of shooting an icy snowball
	 *  @param baseDamage Base damage for the snowball attack. */
	public IcySnowballSkill (double snowballChance, double baseDamage)
	{
		super();
		this.snowballs = new HashMap <UUID, Projectile> ();
		this.snowballChance = snowballChance;
		this.baseSnowballDamage = baseDamage;
	}
	
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		if (this.snowballs.containsKey(source.getUniqueId()))
		{
			this.snowballs.remove(source.getUniqueId());
			
			// Deal no damage if the target is an ally
			if (damagerEquipment.isAlliesOf(damager, target, targetEquipment))
			{
				return 0;
			}
			
			ParticleUtil.spawnColoredParticlesOnEntity(target, 15, 0, 0, 0, Particle.SNOW_SHOVEL);
			ParticleUtil.spawnColoredParticlesOnEntity(target, 15, 0, 0, 0, Particle.SNOWBALL);
			ParticleUtil.spawnColoredParticlesOnEntity(target, 15, 0, 0, 0, Particle.CRIT);
			target.getWorld().playSound(target.getLocation(), Sound.BLOCK_SNOW_BREAK, 1.0f, 1.0f);
			target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 0.8f, 1.0f);
			target.addPotionEffect(new PotionEffect (PotionEffectType.SLOW, this.slowDuration, 0));
			return this.baseSnowballDamage + this.calculateOffset();
		}
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		return 0;
	}

	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{
		if (projectile.getType() != EntityType.SNOWBALL || this.random.nextDouble() > this.snowballChance)
		{
			return;
		}
		
		// Adds projectile's UUID into the snowball UUID table
		this.snowballs.put(projectile.getUniqueId(), projectile);
		
		// Plays a particle effect indicating the skillcaster has just fired a special snowball
		ParticleUtil.spawnColoredParticlesOnEntity(shooter, 15, 173, 216, 230, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(shooter, 15, 255, 255, 255, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(shooter, 10, 0, 0, 0, Particle.SPELL_INSTANT);
		shooter.getWorld().playSound(shooter.getLocation(), Sound.ENTITY_SNOW_GOLEM_HURT, 1.0f, 1.0f);
	}
	
	/** Cleanup routine every second for removing all dead snowballs that never hit their targets */
	@Override
	public void update (LivingEntity caster)
	{
		super.update(caster);
		
		List <UUID> deadSnowballs = new ArrayList <UUID> ();
		for (Projectile projectile : this.snowballs.values())
		{
			if (projectile == null || projectile.isDead())
			{
				deadSnowballs.add(projectile.getUniqueId());
			}
		}
		
		for (UUID uuid : deadSnowballs)
		{
			this.snowballs.remove(uuid);
		}
	}
	
	/** Calculates the damage of a thrown snowball using the assigned base damage
	 *  added with a random offset based on the damage multiplier. */
	public double calculateOffset ()
	{
		return this.damageMultiplierOffset * 
				this.random.nextInt ((int) Math.floor(this.baseSnowballDamage * this.damageMultiplierOffset) + 1);
	}
}
