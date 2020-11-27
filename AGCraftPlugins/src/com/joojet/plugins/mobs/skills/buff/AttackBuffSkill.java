package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import com.joojet.plugins.mobs.enums.MonsterClassifier;

public class AttackBuffSkill extends AbstractBuffSkill 
{
	
	/** A monster skill that applies a strength buff to the caster itself and its
	 *  surrounding allies. */
	public AttackBuffSkill (int amplifier, int duration, int cooldown)
	{
		super (PotionEffectType.INCREASE_DAMAGE, duration, amplifier, 30, cooldown, 8);
	}
	
	/** Only use this skill if any surrounding player's threat score exceeds mythic level */
	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) 
	{
		return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}

	@Override
	protected void playBuffAnimation(LivingEntity entity) {
		this.spawnColoredParticlesOnEntity(entity, 30, 64, 0, 0, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0F, 1.0F);
	}

}
