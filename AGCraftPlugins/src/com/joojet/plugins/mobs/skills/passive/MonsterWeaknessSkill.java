package com.joojet.plugins.mobs.skills.passive;

import java.util.EnumSet;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;

/**
 *  Allows monsters weilding this skill to deal bonus damage towards a specific set of EntityTypes
 **/
public class MonsterWeaknessSkill extends AbstractPassiveSkill implements PassiveAttack
{
	/** Bonus damage applied to all attacked directed to this skills' set entities */
	protected double modifier;
	/** A set of EntityTypes this monster can deal bonus damage to */
	protected EnumSet <EntityType> entities;
	
	public MonsterWeaknessSkill (double modifier, EntityType... types)
	{
		this.modifier = modifier;
		this.entities = EnumSet.noneOf(EntityType.class);
		
		for (EntityType type : types)
		{
			this.entities.add(type);
		}
	}
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		if (this.entities.contains(target.getType()))
		{
			return damage * this.modifier;
		}
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
