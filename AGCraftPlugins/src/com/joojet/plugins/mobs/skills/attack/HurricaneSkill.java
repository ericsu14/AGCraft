package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.runnable.LaunchCustomArrowRunnable;

public class HurricaneSkill extends AbstractAttackSkill {

	/** Allows the custom monster to unleash a hurricane of arrows to a target. */
	public HurricaneSkill(int range, int cooldown, int maxUses, int weight, double arrowDamage) 
	{
		super(range, cooldown, maxUses, weight);
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter) 
	{	
		if (enemies.isEmpty())
		{
			return;
		}
		
		LivingEntity target = enemies.get(this.random.nextInt(enemies.size()));
		
		if (target != null)
		{
			caster.getWorld().spawnParticle(Particle.SWEEP_ATTACK, caster.getLocation(), 4, 0.1, 0.1, 0.1);
			new LaunchCustomArrowRunnable (caster, target, 12, 8.0, monsterTypeInterpreter).runTaskTimer(AGCraftPlugin.plugin, 20, 10);
		}
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.isEmpty();
	}

}
