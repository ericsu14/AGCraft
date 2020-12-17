package com.joojet.plugins.mobs.skills.buff;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class SelfHealSkill extends AbstractBuffSkill {
	protected double threshold;
	
	/** Creates a self heal skill, allowing the mob to self heal once its health reaches below a certain threshold
	 *  @param potionStrength - Strength level of the healing effect
	 *  @param cooldown - Skill's cooldown
	 *  @param weight - Skill's priority weight
	 *  @param threshold - Min. threshold where the caster's health needs to be in order to use this skill */
	public SelfHealSkill(int potionStrength, int cooldown, int weight, double threshold) 
	{
		super(PotionEffectType.HEAL, 0, potionStrength, 0, cooldown, weight);
		this.threshold = threshold;
	}
	

	@Override
	protected void playBuffAnimation(LivingEntity entity) {
		entity.getWorld().spawnParticle(Particle.HEART, entity.getEyeLocation(), 10, 1.0, 1.0, 1.0);
		this.spawnColoredParticlesOnEntity(entity, 30, 0, 255, 0, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getEyeLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1.0f, 1.0f);
	}

	@Override
	protected String getBuffText() {
		// TODO Auto-generated method stub
		return ChatColor.BOLD + "" + ChatColor.GREEN + "SELF HEAL";
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) {
		// TODO Auto-generated method stub
		return (caster.getHealth() / caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) <= this.threshold;
	}

}
