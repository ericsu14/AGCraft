package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class PiercingBlowSkill extends AbstractPassiveSkill implements PassiveProjectile 
{
	/** Applies the piercing blow effect to any projectile shot by custom monsters who have this effect enabled.
	 *  Piercing blow shots are always critical hits, have increased knockback power, and have a piercing effect
	 *  allowing the arrow to pierce through shields.*/
	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{	
		if (shooter == null || shooterEquipment == null || projectile == null || 
				!(projectile instanceof AbstractArrow))
		{
			return;
		}
		
		AbstractArrow arrow = (AbstractArrow) projectile;
		
		if (shooterEquipment != null && shooterEquipment.containsStat(MonsterStat.ARROW_PIERCING_CHANCE))
		{
			boolean pierce = this.getRandomGenerator().nextDouble() <= shooterEquipment.getStat(MonsterStat.ARROW_PIERCING_CHANCE);
			if (pierce)
			{
				arrow.setCritical(true);
				arrow.setPierceLevel(1);
				arrow.setKnockbackStrength(arrow.getKnockbackStrength() + 1);
				
				// Give an audio and visual cue that the mob is using a piercing arrow
				Location entityLocation = shooter.getEyeLocation();
				shooter.getWorld().playSound(entityLocation, Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
				shooter.getWorld().spawnParticle(Particle.SWEEP_ATTACK, entityLocation, 1, 0.1, 0.1, 0.1);
				ParticleUtil.spawnColoredParticlesOnEntity(shooter, 8, 128, 0, 0, Particle.SPELL_MOB);
			}
		}
	}

}
