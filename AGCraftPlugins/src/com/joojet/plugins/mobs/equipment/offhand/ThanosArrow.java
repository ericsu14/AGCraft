package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class ThanosArrow extends TippedArrow 
{

	public ThanosArrow(ChatColor color) 
	{
		super(EquipmentType.THANOS_ARROW, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setColor(Color.fromRGB(75, 0, 130));
		this.addPotionEffect(PotionEffectType.WITHER, 70, 1);
		this.setDisplayName("Thanos Arrow");
		this.addLoreToItemMeta("Will snap the enemy's health in half...");
	}

}
