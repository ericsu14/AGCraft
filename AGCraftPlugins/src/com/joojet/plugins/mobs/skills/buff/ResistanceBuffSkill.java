package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.MonsterClassifier;

public class ResistanceBuffSkill extends AbstractBuffSkill
{
	
	/** Applies a resistance buff to the caster itself and all of its allies surrounding it within
	 *  a radius specified by range.
	 *  @param amplifier - Strength of the potion effect
	 *  @param duration - Duration of the potion effect (in seconds)
	 *  @param range - AOE radius of the buff effect, which will affect all allied mobs within a certain radius of the caster
	 *  @param cooldown - Skill cooldown before the buff can be used again (in seconds)
	 *  @param weight - Priority of this skill */
	
	public ResistanceBuffSkill(int amplifier, int duration, int range, int cooldown, int weight) 
	{
		super(PotionEffectType.DAMAGE_RESISTANCE, duration, amplifier, range, cooldown, weight);
	}

	@Override
	protected void playBuffAnimation(LivingEntity entity) 
	{
		this.spawnColoredParticlesOnEntity(entity, 30, 128, 128, 128, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0F, 1.0F);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies, ArrayList<LivingEntity> enemies) 
	{
		return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}
	
	@Override
	protected String getBuffText() {
		return ChatColor.GRAY + "" + ChatColor.BOLD + "🛡 DEFENSE" + ChatColor.GOLD + " UP";
	}
}
