package com.joojet.plugins.mobs.skills.attack.potionthrow;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.ThrowablePotionType;
import com.joojet.plugins.mobs.equipment.potions.PainfulMocktail;
import com.joojet.plugins.mobs.equipment.potions.SnakeVenomPotion;
import com.joojet.plugins.mobs.skills.enums.TargetSelector;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterLineOfSight;

public class MrJohnsonPotionSkill extends AbstractThrowPotionSkill
{
	
	public MrJohnsonPotionSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight, TargetSelector.ENEMIES);
	}

	@Override
	public void initializePotionsList() 
	{
		this.addPotion(new SnakeVenomPotion (), 60, ThrowablePotionType.SPLASH);
		this.addPotion(new PainfulMocktail (), 30, ThrowablePotionType.SPLASH);
		this.addPotion(new SnakeVenomPotion(), 10, ThrowablePotionType.LINGERING);
	}

	@Override
	public List<LivingEntity> getTargets(LivingEntity caster, List<LivingEntity> enemies) 
	{
		return enemies.stream().
				filter(entity -> !this.undeadMonsters.contains(entity.getType())).
				filter(new FilterLineOfSight (caster)).
				sorted (new ClosestProximity (caster.getLocation().clone())).collect(Collectors.toList());
	}

	@Override
	public void playCasterAnimationEffects(LivingEntity caster, DamageDisplayListener damageDisplayListener) 
	{
		damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + "" + ChatColor.BOLD + "I will show you a spiritual fantasy...");
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_WITCH_THROW, 1.0f, 1.0f);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 0, 0, 0, Particle.SPELL_INSTANT);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 0, 0, 0, Particle.SPELL_WITCH);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !this.getTargets(caster, enemies).isEmpty();
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, 0.60);
	}

}
