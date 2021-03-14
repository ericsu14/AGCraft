package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class USCTracerSkill extends AbstractPassiveSkill implements PassiveAttack
{
	/** Applies a USC tracer hit effect upon attacking any mob */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(target, 6, 153, 27, 30, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 6, 255, 204, 0, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 6, 0, 0, 0, Particle.SMOKE_NORMAL);
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return 0;
	}

}
