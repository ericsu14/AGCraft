package com.joojet.plugins.mobs.skills.attack;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.runnable.LaunchCustomArrowRunnable;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterLineOfSight;

public class HurricaneSkill extends AbstractAttackSkill 
{
	/** Total amount of arrows fired in this skill */
	protected int ammo;
	/** Arrow damage multipler applied to base damage of shot arrows */
	protected double arrowDamageMultipler;
	
	/** Allows the custom monster to unleash a hurricane of arrows to a target. */
	public HurricaneSkill(int range, int cooldown, int maxUses, int weight, int ammo, double arrowDamageMultipler) 
	{
		super(range, cooldown, maxUses, weight);
		this.ammo = ammo;
		this.arrowDamageMultipler = arrowDamageMultipler;
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{	
		if (enemies.isEmpty())
		{
			return;
		}
		
		List <LivingEntity> targets = enemies.stream().
				filter(new FilterLineOfSight (caster)).
				sorted(new ClosestProximity (caster.getLocation().clone())).
				collect(Collectors.toList());
		
		LivingEntity target = targets.get(0);
		
		// Update base arrow damage to the one found on the entity's mob equipment
		double arrowDamage = 4.0 * this.arrowDamageMultipler;
		MobEquipment mobEquipment = monsterTypeInterpreter.getMobEquipmentFromEntity(caster);
		if (mobEquipment != null && mobEquipment.containsStat(MonsterStat.BASE_ARROW_DAMAGE)) 
		{
			arrowDamage = mobEquipment.getStat(MonsterStat.BASE_ARROW_DAMAGE) * this.arrowDamageMultipler;
		}
		
		if (target != null)
		{
			caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);
			caster.getWorld().spawnParticle(Particle.SWEEP_ATTACK, caster.getLocation(), 4, 0.1, 0.1, 0.1);
			damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + "" + ChatColor.BOLD + "HURRICANE!");
			new LaunchCustomArrowRunnable (caster, target, this.ammo, arrowDamage, monsterTypeInterpreter) {
				@Override
				public void playAnimationEffects() 
				{
					this.caster.getWorld().spawnParticle(Particle.CRIT_MAGIC, caster.getLocation(), 16, 1.0, 0.5, 0.0);
					this.caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, 1.0F);
					this.caster.getWorld().spawnParticle(Particle.SWEEP_ATTACK, caster.getLocation(), 1, 0.0, 0.0, 0.0);
				}
				
			}.runTaskTimer(AGCraftPlugin.plugin, 20, 8);
		}
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.stream().filter(new FilterLineOfSight (caster)).collect(Collectors.toList()).isEmpty();
	}

	/** This skill can only be used if the caster is holding a bow */
	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.hasBow(caster);
	}

}
