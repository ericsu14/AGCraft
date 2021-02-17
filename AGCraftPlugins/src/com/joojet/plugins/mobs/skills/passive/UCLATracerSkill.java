package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class UCLATracerSkill extends AbstractPassiveSkill implements PassiveAttack
{
	
	/** Adds a UCLA themed particle effect upon being attacked by a UCLA themed mob */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 39, 116, 174, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 255, 209, 0, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 6, 0, 0, 0, Particle.SMOKE_NORMAL);
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		return 0;
	}

}
