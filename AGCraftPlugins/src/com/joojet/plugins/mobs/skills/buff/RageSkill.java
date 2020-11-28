package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RageSkill extends AbstractBuffSkill {
	
	/** Used to determine if a mob is enraged */
	private boolean enraged;
	/** Health percentage threshold the monster's health needs to reach before this skill is activated */
	private double threshold;
	
	/** Allows a monster to temporarily increase its strength and health once its
	 *  base health drops below a certain percentage.
	 *  @param amplifier - Power of the strength effect
	 *  @param duration - Duration of the monster's rage mode */
	public RageSkill (int amplifier, int duration, double threshold)
	{
		super (PotionEffectType.INCREASE_DAMAGE, duration, amplifier, 1, duration, 8);
		this.maxUses = 1;
		this.currentUsage = 1;
		this.enraged = false;
		this.threshold = threshold;
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
		entity.addPotionEffect(new PotionEffect (PotionEffectType.ABSORPTION, duration, 4, false, true));
		this.enraged = true;
	}

	/** Activates rage mode once the monster's health drops below 35%, which increases damage */
	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) {
		return (caster.getHealth() / caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) <= this.threshold;
	}
	
	/** Overrides the update function to play a small animation when mob is enraged */
	@Override
	public void update (LivingEntity caster)
	{
		super.update(caster);
		if (enraged && this.random.nextBoolean() && this.cooldownTick > 0)
		{
			this.spawnColoredParticlesOnEntity(caster, 10, 0, 0, 0, Particle.SMOKE_LARGE);
			this.spawnColoredParticlesOnEntity(caster, 15, 0, 0, 0, Particle.FLAME);
		}
	}
	
	@Override
	protected String getBuffText() {
		return ChatColor.RED + "" + ChatColor.BOLD + "☠ RAGE" + ChatColor.GOLD + " ENGAGED";
	}

}
