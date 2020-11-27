package com.joojet.plugins.mobs.damage.entities;

import java.text.DecimalFormat;
import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.damage.enums.DamageType;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class DamageDisplayEntity extends MobEquipment 
{
	
	/** Creates a new Damage Display entity with a configurable nametag
	 *  @param name - Nametag that is to be displayed with this damage display entity */
	public DamageDisplayEntity (String name)
	{
		super (MonsterType.DAMAGE_DISPLAY_ENTITY);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.color = ChatColor.RESET;
		this.name = name;
	}
	
	/** Creates a new MobEquipment instance for a Damage Display entity.
	 *  	@param finalDamage - Final damage captured by event listener
	 *  	@param damageType - Type of damage this event is caused by */
	public DamageDisplayEntity (double finalDamage, DamageType damageType)
	{
		super (MonsterType.DAMAGE_DISPLAY_ENTITY);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.color = ChatColor.RESET;
		
		StringBuilder displayName = new StringBuilder ();
		if (damageType.hasSymbol())
		{
			displayName.append(damageType.toString());
			displayName.append(" ");
		}
		if (damageType != DamageType.SUICIDE)
		{
			DecimalFormat df = new DecimalFormat ("#.##");
			displayName.append(damageType.getDamageColor());
			displayName.append(df.format(finalDamage));
		}
		else
		{
			displayName.append(damageType.getDamageColor());
			displayName.append("SUICIDE");
		}
		if (damageType.hasSymbol())
		{
			displayName.append(" ");
			displayName.append(damageType.getReversedSymbols());
		}
		this.name = displayName.toString().trim();
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		// TODO Auto-generated method stub
		
	}
}
