package com.joojet.plugins.mobs.skills.utility;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

public abstract class WeaponSwitchSkill extends AbstractSkill 
{
	/** Weapon to be switched */
	protected Equipment weapon;
	
	/** Allows a custom mob to switch to a different weapon
	 *  @param cooldown Cooldown time for this skill in seconds
	 *  @param maxUses Max. amount of times this skill can be used
	 *  @param weight Weight of this skill
	 *  @param weapon The weapon to be equipped */
	public WeaponSwitchSkill(int cooldown, int maxUses, int weight, Equipment weapon) 
	{
		super(SkillPropetry.UTILITY, 0, cooldown, maxUses, weight);
		this.weapon = weapon;
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter) {
		this.playAnimation(caster, damageDisplayListener);
		caster.getEquipment().setItemInMainHand(this.weapon);
	}
	
	/** The animation that is to be played once the mob switches to a different weapon
	 *  @param caster Entity casting this skill
	 *  @param damageDisplayListener Damage display listener to display visual strings above the caster */
	protected abstract void playAnimation (LivingEntity caster, DamageDisplayListener damageDisplayListener);
}
