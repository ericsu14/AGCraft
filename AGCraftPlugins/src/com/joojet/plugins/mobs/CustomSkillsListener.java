package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class CustomSkillsListener extends AGListener {
	/** A reference to the monster type interpreter defined in the main plugin class */
	protected MonsterTypeInterpreter monsterInterpreter;
	
	public CustomSkillsListener (MonsterTypeInterpreter monsterInterpreter)
	{
		this.monsterInterpreter = monsterInterpreter;
	}
	
	@Override
	public void loadConfigVariables(ServerConfigFile config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}
	
	public void useCustomSkill (LivingEntity caster, AbstractSkill skill)
	{
		ArrayList <LivingEntity> allies = new ArrayList <LivingEntity> ();
		ArrayList <LivingEntity> enemies = new ArrayList <LivingEntity> ();
		List <Entity> surroundingEntities = caster.getNearbyEntities(skill.getRange(), skill.getRange(), skill.getRange());
		
		boolean isAlly = false;
		for (Entity entity : surroundingEntities)
		{
			isAlly = false;
			if (!(entity instanceof LivingEntity))
			{
				continue;
			}
			
			LivingEntity livingEntity = (LivingEntity) entity;
			MobEquipment entityEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(livingEntity);
			
			// Now we need to check if the entity is an ally or an enemy to the caster
			// If the caster is a player, then the entity is an ally if it is also a player or
			// has it in its ignore list
			if (caster instanceof Player)
			{
				isAlly = ((livingEntity instanceof Player) || 
						(entityEquipment != null && entityEquipment.getIgnoreList().contains(EntityType.PLAYER)));
			}
			// Otherwise, if the caster is any other living entity, the entity is an ally if:
			// 1. The caster is in the entity's ignore list
			// 2. The entity is in the caster's ignore list
			// 3. The entity is not in the caster's list of rivaling factions
			else
			{
				MobEquipment casterEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(caster);
				
				isAlly = ((entityEquipment != null && entityEquipment.getIgnoreList().contains(caster.getType())) ||
						  (casterEquipment != null && casterEquipment.getIgnoreList().contains(livingEntity.getType())) ||
						  ((casterEquipment != null && entityEquipment != null) && !casterEquipment.isRivalsOf(entityEquipment)));
				
				// Check for cases where the entity is not a custom mob and the entity is still not marked an ally
				// from the previous checks. If so, the entity is an ally if it is not in the caster's hit list.
				// This allows regular zombies and skeletons to be treated like allies, but not players, as the player
				// must be in the caster's ignore list to be treated like an ally at this point.
				if (!isAlly && casterEquipment != null && entityEquipment == null)
				{
					isAlly = !(casterEquipment.getHitList().contains(livingEntity.getType()));
				}
			}
			
			if (isAlly)
			{
				allies.add(livingEntity);
			}
			else
			{
				enemies.add(livingEntity);
			}
		}
		
		skill.useSkill(caster, allies, enemies);
	}

}
