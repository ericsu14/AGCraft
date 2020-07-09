package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.DesertSandals;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WanderingHusk extends MobEquipment
{
	public WanderingHusk ()
	{
		this.name = "Wandering Husk";
		this.color = ChatColor.GREEN;
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.boots = new DesertSandals (this.color);
	}
}
