package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RageSkill extends AbstractBuffSkill {
	
	public RageSkill (int amplifier)
	{
		super (PotionEffectType.INCREASE_DAMAGE, 1000, amplifier, 1, Integer.MAX_VALUE, 8);
		this.maxUses = 1;
	}
	
	@Override
	protected void playBuffAnimation(LivingEntity entity) {
		this.spawnColoredParticlesOnEntity(entity, 30, 255, 255, 255, Particle.VILLAGER_ANGRY);
		this.spawnColoredParticlesOnEntity(entity, 30, 64, 0, 0, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
	}
	
	/** Overrides the generic potion effect skill, as we are going to apply multiple effects */
	@Override
	protected void applyPotionEffect (LivingEntity entity, PotionEffectType potion, int duration, int strength)
	{
		entity.addPotionEffect(new PotionEffect (PotionEffectType.INCREASE_DAMAGE, duration, strength, false, true));
		entity.addPotionEffect(new PotionEffect (PotionEffectType.GLOWING, duration, 0, false, true));
		entity.addPotionEffect(new PotionEffect (PotionEffectType.ABSORPTION, 300, 4, false, true));
	}

	/** Activates rage mode once the monster's health drops below 35%, which increases damage */
	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) {
		return (caster.getHealth() / caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) <= 0.35;
	}

}
