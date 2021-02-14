package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.runnable.LaserBeamRunnable;

public class LazerBeamAttack extends AbstractAttackSkill
{
	/** Amount of time (in ticks) the laser beam will charge up before attacking */
	protected final int delayTicks;
	
	public LazerBeamAttack(int range, int cooldown, int maxUses, int weight, int delayTicks) 
	{
		super(range, cooldown, maxUses, weight);
		this.delayTicks = delayTicks;
	}
	
	/** Amount of ticks this laser beam will charge up before attacking */
	public int getDelayTicks ()
	{
		return this.delayTicks;
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		LivingEntity enemy = enemies.get(this.random.nextInt(enemies.size()));
		if (enemy != null)
		{
			new LaserBeamRunnable (caster, monsterTypeInterpreter.getMobEquipmentFromEntity(caster),
					enemy, this).runTaskTimer(AGCraftPlugin.plugin, 20, 4);
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
		return true;
	}

}
