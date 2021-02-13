package com.joojet.plugins.mobs.skills.visual;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.util.particle.ParticleUtil;


/** This skill does nothing but emit fire particles around the entity until it dies. */
public class FireAura extends AbstractVisualSkill 
{
	@Override
	protected void displayVisualEffects(LivingEntity caster) 
	{
		// Randomly display fire particles around the entity
		if (this.random.nextBoolean())
		{
			ParticleUtil.spawnColoredParticlesOnEntity(caster, 20, 0, 0, 0, Particle.FLAME);
		}
	}
}
