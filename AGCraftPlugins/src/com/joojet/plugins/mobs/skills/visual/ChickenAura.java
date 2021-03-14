package com.joojet.plugins.mobs.skills.visual;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class ChickenAura extends AbstractVisualSkill implements PassiveAttack
{
	@Override
	protected void displayVisualEffects(LivingEntity caster) 
	{
		if (this.random.nextDouble() <= 0.10)
		{
			ParticleUtil.spawnColoredParticlesOnEntity(caster, 4, 0, 0, 0, Particle.FIREWORKS_SPARK);
		}
	}

	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		damager.getWorld().playSound(damager.getLocation(), Sound.ENTITY_CHICKEN_HURT, 0.6f, (float) (this.random.nextDouble() + 1.0));
		damager.getWorld().playSound(damager.getLocation(), Sound.ENTITY_PLAYER_ATTACK_WEAK, 0.6f, 1.0f);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 2, 0, 0, 0, Particle.EXPLOSION_NORMAL);
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return 0;
	}

}
