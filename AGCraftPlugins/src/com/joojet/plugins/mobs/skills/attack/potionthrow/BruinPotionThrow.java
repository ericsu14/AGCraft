package com.joojet.plugins.mobs.skills.attack.potionthrow;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.ThrowablePotionType;
import com.joojet.plugins.mobs.equipment.potions.BruinResistancePotion;
import com.joojet.plugins.mobs.equipment.potions.BruinStrengthPotion;
import com.joojet.plugins.mobs.skills.enums.TargetSelector;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterLineOfSight;

public class BruinPotionThrow extends AbstractThrowPotionSkill {

	public BruinPotionThrow(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight, TargetSelector.ALLIES);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializePotionsList() 
	{
		this.addPotion(new BruinStrengthPotion(), 60, ThrowablePotionType.SPLASH);
		this.addPotion(new BruinResistancePotion(), 40, ThrowablePotionType.SPLASH);
	}

	@Override
	public List<LivingEntity> getTargets(LivingEntity caster, List<LivingEntity> entities) 
	{
		return entities.stream().filter(new FilterLineOfSight(caster)).
				filter(ent -> !ent.equals(caster)).
				sorted(new ClosestProximity(caster.getLocation().clone())).toList();
	}

	@Override
	public void playCasterAnimationEffects(LivingEntity caster, DamageDisplayListener damageDisplayListener) 
	{
		damageDisplayListener.displayStringAboveEntity(caster, StringUtil.alternateTextColors("Time to clap some trojans!", TextPattern.WORD, ChatColor.AQUA, ChatColor.GOLD));
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_WITCH_THROW, 1.0f, 1.0f);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 0, 0, 0, Particle.SPELL_INSTANT);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 0, 0, 0, Particle.SPELL_WITCH);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !allies.isEmpty() && !enemies.isEmpty();
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, 0.75);
	}

}
