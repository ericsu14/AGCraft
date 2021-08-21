package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class FireResistancePotion extends SplashPotionEquipment
{

	public FireResistancePotion() 
	{
		super(EquipmentType.FIRE_RESISTANCE_POTION, ChatColor.GOLD);
	}

	@Override
	protected void addPotionData() 
	{
		this.setColor(Color.ORANGE);
		this.setDisplayName("Potion of Fire Resistance");
		this.addPotionEffect(PotionEffectType.FIRE_RESISTANCE, 1800 , 0);
	}

}
