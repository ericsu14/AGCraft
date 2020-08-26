package com.joojet.plugins.mobs.damage.entities;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class DamageDisplayEntity extends MobEquipment 
{
	/** Creates a new MobEquipment instance for a Damage Display entity.
	 *  	@param finalDamage - Final damage captured by event listener
	 *  	@param critical - Determines if the attack is a critical hit */
	public DamageDisplayEntity (double finalDamage, boolean critical)
	{
		super (MonsterType.DAMAGE_DISPLAY_ENTITY);
		this.addMobFlags(MobFlag.SHOW_NAME);
		StringBuilder displayName = new StringBuilder ();
		if (critical)
		{
			this.color = ChatColor.GOLD;
			displayName.append("✰ ");
		}
		else
		{
			this.color = ChatColor.WHITE;
		}
		DecimalFormat df = new DecimalFormat ("#.##");
		displayName.append(df.format(finalDamage));
		this.name = displayName.toString();
	}
}
