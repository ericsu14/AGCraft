package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentTypes;

public class ThanosArrow extends TippedArrow 
{

	public ThanosArrow(ChatColor color) 
	{
		super(EquipmentTypes.THANOS_ARROW, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setColor(Color.fromRGB(75, 0, 130));
		this.addPotionEffect(PotionEffectType.WITHER, 70, 2);
		this.addPotionEffect(PotionEffectType.HARM, 1, 1);
		this.setDisplayName("Thanos Arrow");
		this.addLoreToItemMeta("Will snap the enemy's health in half...");
	}

}
