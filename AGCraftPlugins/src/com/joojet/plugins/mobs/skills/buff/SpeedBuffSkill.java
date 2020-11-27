package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.MonsterClassifier;

public class SpeedBuffSkill extends AbstractBuffSkill {
	
	public SpeedBuffSkill (int amplifier, int duration, int cooldown)
	{
		super (PotionEffectType.SPEED, duration, amplifier, 30, cooldown, 8);
	}
	
	@Override
	protected void playBuffAnimation(LivingEntity entity) 
	{
		this.spawnColoredParticlesOnEntity(entity, 30, 224, 255, 255, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0F, 1.0F);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) {
		return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}

}
