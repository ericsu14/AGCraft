package com.joojet.plugins.mobs.interfaces;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public interface CustomAttribute 
{
	/** Applies a custom attribute to an Entity based on the propetries of a custom MobFlag or MobStat
	 *  @param entity The living entity being modified
	 *  @param entityEquipment The mobEquipment instance belonging to the entity 
	 *  @param bossBarController A reference to the boss bar controller used to create boss bar events */
	public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController);
}
