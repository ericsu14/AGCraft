package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;

public class MeteorSkill extends AbstractAttackSkill implements PassiveAttack
{
	/** Explosion power of the meteors fired from this skill */
	public final float power;
	/** True if this skill is actively being used */
	protected boolean isActive;
	
	public MeteorSkill(int range, int cooldown, int maxUses, int weight, float power) 
	{
		super(range, cooldown, maxUses, weight);
		this.isActive = false;
		this.power = power;
	}
	
	/** Summons a meteor shower, targeting random enemies around a surrounding area */
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/** Cancels damage events towards allies */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{	
		return damagerEquipment.isAlliesOf(damager, target, targetEquipment) ? Double.MIN_VALUE : 0;
	}

	/** Cancels incoming damage events if the caster is currently using this skill */
	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return this.isActive ? Double.MIN_VALUE : 0;
	}
	
	/** Changes the active flag of this skill */
	public void setActive (boolean flag)
	{
		this.isActive = flag;
	}

}
