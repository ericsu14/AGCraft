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
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class SummonTrojansSkill extends AbstractSummonSkill 
{

	public SummonTrojansSkill(int range, int cooldown, int maxUses, int weight, int maxSummons) 
	{
		super(range, cooldown, maxUses, weight, maxSummons);
	}

	@Override
	public void initializeSummons() 
	{
		this.addSummon(MonsterType.USC_WARRIOR, EntityType.HUSK, 30);
		this.addSummon(MonsterType.USC_ARCHER, EntityType.SKELETON, 40);
		this.addSummon(MonsterType.SPIRIT_OF_TROY, EntityType.HUSK, 20);
		this.addSummon(MonsterType.ETERNAL_TROJAN_ARCHER, EntityType.SKELETON, 5);
		this.addSummon(MonsterType.TROJAN_WARRIOR, EntityType.HUSK, 5);
	}

	@Override
	public void playSkillCasterAnimation(LivingEntity caster, DamageDisplayListener damageDisplayListener) 
	{
		damageDisplayListener.displayStringAboveEntity(caster, StringUtil.alternateTextColors("RALLY UP THE TROOPS! WE NEED TO PREVAIL!", TextPattern.WORD,
				ChatColor.RED, ChatColor.GOLD));
		caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, caster.getLocation(), 20, 0.1, 0.1, 0.1);
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);
	}

	@Override
	public void playSummonAnimation(LivingEntity entity, DamageDisplayListener damageDisplayListener) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 6, 255, 244, 229, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 6, 255, 204, 0, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 6, 255, 244, 229, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 8, 0, 0, 0, Particle.SPELL_INSTANT);
		damageDisplayListener.displayStringAboveEntity(entity, StringUtil.alternateTextColors("Fight on forever!", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD));
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f);
	}

	@Override
	public String getSentMessage(LivingEntity caster) 
	{
		return caster.getCustomName() + ChatColor.GOLD + " is rallying the troops!";
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.isEmpty();
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, 0.70);
	}

}
