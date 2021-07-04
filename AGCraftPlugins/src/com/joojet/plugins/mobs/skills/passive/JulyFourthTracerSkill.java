package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class JulyFourthTracerSkill extends AbstractPassiveSkill implements PassiveAttack
{

	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 128, 0, 0, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 255, 255, 255, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 0, 0, 128, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 6, 0, 0, 0, Particle.FIREWORKS_SPARK);
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}
