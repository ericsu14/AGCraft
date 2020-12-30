package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.runnable.LaunchCustomArrowRunnable;

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
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter) 
	{	
		if (enemies.isEmpty())
		{
			return;
		}
		
		List <LivingEntity> targets = this.filterByLineOfSight(enemies, caster);
		LivingEntity target = targets.get(this.random.nextInt(targets.size()));
		
		// Update base arrow damage to the one found on the entity's mob equipment
		double arrowDamage = 4.0 * this.arrowDamageMultipler;
		MobEquipment mobEquipment = monsterTypeInterpreter.getMobEquipmentFromEntity(caster);
		if (mobEquipment != null && mobEquipment.containsStat(MonsterStat.BASE_ARROW_DAMAGE)) 
		{
			arrowDamage = mobEquipment.getStat(MonsterStat.BASE_ARROW_DAMAGE) * this.arrowDamageMultipler;
		}
		
		if (target != null)
		{
			caster.getWorld().spawnParticle(Particle.SWEEP_ATTACK, caster.getLocation(), 4, 0.1, 0.1, 0.1);
			new LaunchCustomArrowRunnable (caster, target, this.ammo, arrowDamage, monsterTypeInterpreter) {
				@Override
				public void playAnimationEffects() 
				{
					this.caster.getWorld().spawnParticle(Particle.TOTEM, caster.getLocation(), 10, 0.5, 0.5, 0.5);
					this.caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, 1.0F);
				}
				
			}.runTaskTimer(AGCraftPlugin.plugin, 20, 10);
		}
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !this.filterByLineOfSight(enemies, caster).isEmpty();
	}

}
