package com.joojet.plugins.mobs.skills.summon;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterType;

public class SummonBruinSkill extends AbstractSummonSkill {

	public SummonBruinSkill(int range, int cooldown, int maxUses, int weight, int maxSummons) 
	{
		super(range, cooldown, maxUses, weight, maxSummons);
	}

	@Override
	public void initializeSummons() 
	{
		this.addSummon(MonsterType.UCLA_JOCK, EntityType.HUSK, 14);
		this.addSummon(MonsterType.UCLA_ARCHER, EntityType.SKELETON, 6);
	}

	@Override
	public void playSummonAnimation(LivingEntity entity, DamageDisplayListener damageDisplayListener) {
		entity.getWorld().spawnParticle(Particle.SPELL_INSTANT, entity.getLocation(), 40, 0.5, 0.5, 0.5);
		damageDisplayListener.displayStringAboveEntity(entity, StringUtil.alternateTextColors("GOT U FAM!", TextPattern.WORD,
				ChatColor.AQUA, ChatColor.GOLD));
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 2.0f, 2.0f);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.isEmpty();
	}
	
	/** The caster can only use this skill if its health is below 70% */
	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, 0.70) && 
				this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}

	@Override
	public void playSkillCasterAnimation(LivingEntity caster, DamageDisplayListener damageDisplayListener) {
		damageDisplayListener.displayStringAboveEntity(caster, StringUtil.alternateTextColors("WHERE ARE MY BOIS AT?!?", TextPattern.WORD,
				ChatColor.AQUA, ChatColor.GOLD));
		caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, caster.getLocation(), 20, 0.1, 0.1, 0.1);
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);
	}

	@Override
	public String getSentMessage(LivingEntity caster) 
	{
		return caster.getName() + ChatColor.GOLD + " is calling for help!";
	}

}
