package com.joojet.plugins.mobs.skills.attack;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.skills.runnable.EvokerFangAttackRunnable;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterLineOfSight;

public class EvokerFangSkill extends AbstractAttackSkill implements PassiveAttack
{
	protected double attackDamage;
	
	public EvokerFangSkill(int range, int cooldown, int maxUses, int weight, double attackDamage) 
	{
		super(range, cooldown, maxUses, weight);
		this.attackDamage = attackDamage;
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		List <LivingEntity> filteredTargets = enemies.stream().filter(new FilterLineOfSight (caster)).
				sorted (new ClosestProximity (caster.getLocation().clone())).
				collect(Collectors.toList());
		
		LivingEntity target = filteredTargets.get(0);
		damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + ""  + ChatColor.BOLD + "FANGS!");
		caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, caster.getLocation(), 10, 1.0, 1.0, 0.0, 0.1, null);
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 1.0f, 1.0f);
		new EvokerFangAttackRunnable (caster, target, this.range).runTaskLater(AGCraftPlugin.plugin, 20);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.stream().filter(new FilterLineOfSight (caster)).collect(Collectors.toList()).isEmpty();
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return true;
	}

	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		double bonus = 0.0;
		/** Scales attack damage to the monster's attack strength if attacked by evoker fangs */
		if (source instanceof EvokerFangs)
		{
			ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 0, 0, 0, Particle.SOUL);
			ParticleUtil.spawnColoredParticlesOnEntity(target, 4, 0, 0, 0, Particle.SOUL_FIRE_FLAME);
			bonus += this.attackDamage;
		}
		return bonus;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
