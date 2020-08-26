package com.joojet.plugins.mobs.damage.entities;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.damage.enums.DamageType;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class DamageDisplayEntity extends MobEquipment 
{
	/** Creates a new MobEquipment instance for a Damage Display entity.
	 *  	@param finalDamage - Final damage captured by event listener
	 *  	@param critical - Determines if the attack is a critical hit */
	public DamageDisplayEntity (double finalDamage, DamageType damageType)
	{
		super (MonsterType.DAMAGE_DISPLAY_ENTITY);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.color = ChatColor.RESET;
		
		StringBuilder displayName = new StringBuilder ();
		displayName.append(damageType.toString());
		DecimalFormat df = new DecimalFormat ("#.##");
		displayName.append(ChatColor.WHITE);
		displayName.append(df.format(finalDamage));
		this.name = displayName.toString();
	}
}
