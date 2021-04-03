package com.joojet.plugins.mobs.skills.summon;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

import net.md_5.bungee.api.ChatColor;

public class SummonPiglinArmySkill extends AbstractSummonSkill {

	public SummonPiglinArmySkill(int range, int cooldown, int maxUses, int weight, int maxSummons) 
	{
		super(range, cooldown, maxUses, weight, maxSummons);
	}

	@Override
	public void initializeSummons() 
	{
		this.addSummon(MonsterType.PIGLIN_HUNTER, EntityType.PIGLIN, 60);
		this.addSummon(MonsterType.PIGLIN_SOLDIER, EntityType.PIGLIN, 30);
		this.addSummon(MonsterType.PIGLIN_SOLDIER, EntityType.PIGLIN_BRUTE, 10);
	}

	@Override
	public void playSkillCasterAnimation(LivingEntity caster, DamageDisplayListener damageDisplayListener) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 253, 215, 228, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 120, 193, 101, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(caster, 8, 238, 219, 128, Particle.REDSTONE);
		caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, caster.getLocation(), 20, 0.1, 0.1, 0.1);
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_PIGLIN_ANGRY, 1.0f, 1.0f);
		caster.getWorld().playSound(caster.getLocation(), Sound.EVENT_RAID_HORN, 0.8f, 1.0f);
		damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + "" + ChatColor.BOLD + "WE NEED REINFORCEMENTS!");
	}

	@Override
	public void playSummonAnimation(LivingEntity entity, DamageDisplayListener damageDisplayListener) 
	{
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 8, 253, 215, 228, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 8, 120, 193, 101, Particle.REDSTONE);
		ParticleUtil.spawnColoredParticlesOnEntity(entity, 8, 238, 219, 128, Particle.REDSTONE);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.8f, 1.0f);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PIGLIN_ANGRY, 0.8f, this.random.nextFloat() + 0.5f);
		damageDisplayListener.displayStringAboveEntity(entity, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "REEEEEEEEEE!");
	}

	@Override
	public String getSentMessage(LivingEntity caster) 
	{
		return caster.getCustomName() + ChatColor.YELLOW + " is calling reinforcements!";
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.isEmpty();
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, 0.50);
	}

}
