package com.joojet.plugins.mobs.skills.summon;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class SummonChickenGang extends AbstractSummonSkill {

	public SummonChickenGang(int range, int cooldown, int maxUses, int weight, int maxSummons) 
	{
		super(range, cooldown, maxUses, weight, maxSummons);
	}

	@Override
	public void initializeSummons() 
	{
		this.addSummon(MonsterType.CHICKEN_FIGHTER, EntityType.CHICKEN, 4);
		this.addSummon(MonsterType.FRIEND_CHICKEN, EntityType.CHICKEN, 4);
		this.addSummon(MonsterType.CLUCK_FREAK, EntityType.CHICKEN, 4);
		this.addSummon(MonsterType.CRAZY_NUGGET, EntityType.CHICKEN, 4);
	}

	@Override
	public void playSkillCasterAnimation(LivingEntity caster, DamageDisplayListener damageDisplayListener) 
	{
		damageDisplayListener.displayStringAboveEntity(caster, StringUtil.alternateTextColors("OUT OF YOUR FRIENDS, WHICH ARE YOU?", TextPattern.WORD,
				ChatColor.RED, ChatColor.GOLD));
		caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, caster.getLocation(), 20, 0.1, 0.1, 0.1);
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_CHICKEN_DEATH, 0.8f, 1.0f);
	}

	@Override
	public void playSummonAnimation(LivingEntity entity, DamageDisplayListener damageDisplayListener) 
	{
		entity.getWorld().spawnParticle(Particle.SPELL_INSTANT, entity.getLocation(), 40, 0.5, 0.5, 0.5);
		damageDisplayListener.displayStringAboveEntity(entity, entity.getCustomName());
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 2.0f, 2.0f);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_CHICKEN_AMBIENT, (float) (this.random.nextDouble() + 1.0), 2.0f);
		
		for (Player p : ScanEntities.ScanNearbyPlayers(entity, this.range))
		{
			p.sendMessage(entity.getCustomName());
		}
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

	@Override
	public String getSentMessage(LivingEntity caster) 
	{
		return StringUtil.alternateTextColors("OUT OF YOUR FRIENDS, WHICH ARE YOU?", TextPattern.WORD,
				ChatColor.RED, ChatColor.GOLD);
	}

}
