package com.joojet.plugins.mobs.skills.attack.potionthrow;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.ThrowablePotionType;
import com.joojet.plugins.mobs.equipment.potions.FireResistancePotion;
import com.joojet.plugins.mobs.skills.enums.TargetSelector;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

/**
 * Allows the skillcaster to throw a fire-resistance potion on itself when it is on fire.
 * @author erics
 *
 */
public class FireResistancePotionSkill extends AbstractThrowPotionSkill 
{
	public FireResistancePotionSkill(int cooldown, int maxUses, int weight) 
	{
		super(1, cooldown, maxUses, weight, TargetSelector.SELF);
	}

	@Override
	public void initializePotionsList() 
	{
		this.addPotion(new FireResistancePotion(), 100, ThrowablePotionType.SPLASH);
	}

	@Override
	public List<LivingEntity> getTargets(LivingEntity caster, List<LivingEntity> entities) 
	{
		return entities;
	}

	@Override
	public void playCasterAnimationEffects(LivingEntity caster, DamageDisplayListener damageDisplayListener) 
	{
		damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + "" + ChatColor.BOLD + "Fire... BEGONE!");
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_WITCH_THROW, 1.0f, 1.0f);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 0, 0, 0, Particle.SPELL_INSTANT);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 0, 0, 0, Particle.SPELL_WITCH);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return true;
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return caster.getFireTicks() > 0 && !caster.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE);
	}

}
