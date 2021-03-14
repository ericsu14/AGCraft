package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class MrJohnsonAuraSkill extends AbstractPassiveSkill implements PassiveAttack
{

	/** Adds Mr. Johnson themed particles on all of his attacks */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 4, 3, 11, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 51, 52, 47, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 18, 35, 104, Particle.REDSTONE);
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return 0;
	}

}
