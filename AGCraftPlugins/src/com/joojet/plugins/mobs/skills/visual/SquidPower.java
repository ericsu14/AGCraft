package com.joojet.plugins.mobs.skills.visual;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Squid;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class SquidPower extends AbstractVisualSkill implements PassiveAttack
{
	/** Chance of inflicting the blind status effect upon attacking */
	protected double blindChance;
	
	/** Allows the squid to emit a passive particle effect.
	 *  All of its attacks has a chance of inflicting blindness */
	public SquidPower (double blindChance)
	{
		super ();
		this.blindChance = blindChance;
	}
	
	/** Allows evil squids to have a passive effect that indicates its evilness */
	@Override
	protected void displayVisualEffects(LivingEntity caster) 
	{
		if (this.random.nextDouble() <= 0.30)
		{
			// Let it spill ink more frequently
			ParticleUtil.spawnColoredParticlesOnEntity(caster, 12, 0, 0, 0, Particle.SQUID_INK);
			ParticleUtil.spawnColoredParticlesOnEntity(caster, 4, 0, 0, 0, Particle.SMOKE_NORMAL);
			ParticleUtil.spawnColoredParticlesOnEntity(caster, 6, 0, 0, 0, Particle.SOUL_FIRE_FLAME);
		}
	}

	/** Squids have a chance of inflicting blindness upon attacking */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		if (damager instanceof Squid && this.random.nextDouble() <= this.blindChance)
		{
			target.addPotionEffect(new PotionEffect (PotionEffectType.BLINDNESS, 100, 0));
			ParticleUtil.spawnColoredParticlesOnEntity(target, 8, 0, 0, 0, Particle.SQUID_INK);
			ParticleUtil.spawnColoredParticlesOnEntity(target, 8, 0, 0, 0, Particle.SMOKE_LARGE);
			target.getWorld().playSound(target.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1.0f, 1.0f);
		}
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		return 0;
	}

}
