package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.MonsterClassifier;

public class ResistanceBuffSkill extends AbstractBuffSkill
{
	public ResistanceBuffSkill(int amplifier) 
	{
		super(PotionEffectType.DAMAGE_RESISTANCE, 300, amplifier, 30, 6);
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

}
