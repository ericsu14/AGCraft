package com.joojet.plugins.mobs.skills.attack.potionthrow;

import java.util.List;
import java.util.stream.Stream;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.ThrowablePotionType;
import com.joojet.plugins.mobs.equipment.potions.PainfulMocktail;
import com.joojet.plugins.mobs.equipment.potions.SnakeVenomPotion;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class MrJohnsonPotionSkill extends AbstractThrowPotionSkill
{
	
	public MrJohnsonPotionSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight);
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
		Stream <LivingEntity> targetStream = enemies.stream().filter(entity -> !this.undeadMonsters.contains(entity.getType()));
		targetStream = this.filterByLineOfSight(targetStream, caster);
		targetStream = this.sortByClosestProximity(targetStream, caster);
		return this.convertStreamToList(targetStream);
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
