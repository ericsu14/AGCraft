package com.joojet.plugins.mobs.skills.buff;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class AttackBuffSkill extends AbstractBuffSkill 
{
	/** A monster skill that applies a strength buff to the caster itself and its
	 *  surrounding allies.
	 *  @param amplifier - Strength of the potion effect
	 *  @param duration - Duration of the potion effect (in seconds)
	 *  @param range - AOE radius of the buff effect, which will affect all allied mobs within a certain radius of the caster
	 *  @param cooldown - Skill cooldown before the buff can be used again (in seconds)
	 *  @param weight - Priority of this skill */
	public AttackBuffSkill (int amplifier, int duration, int range, int cooldown, int weight)
	{
		super (PotionEffectType.INCREASE_DAMAGE, duration, amplifier, range, cooldown, weight);
	}
	
	/** Only use this skill if any surrounding player's threat score exceeds mythic level */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies,
			List<LivingEntity> enemies) {
		return true;
	}
	
	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}

	@Override
	protected void playBuffAnimation(LivingEntity entity) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 8, 64, 0, 0, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 0.6F, 1.0F);
	}

	@Override
	protected String getBuffText() 
	{
		return ChatColor.DARK_RED + "" + ChatColor.BOLD + "✦ ATTACK" + ChatColor.GOLD + " UP";
	}

}
