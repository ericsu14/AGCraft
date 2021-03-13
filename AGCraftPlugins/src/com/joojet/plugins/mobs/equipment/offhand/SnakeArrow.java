package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class SnakeArrow extends TippedArrow {

	public SnakeArrow(ChatColor color) 
	{
		super(EquipmentType.SNAKE_ARROW, color);
	}

	@Override
	protected void addPotionData() 
	{
		this.setColor(Color.fromRGB(17, 41, 89));
		this.addPotionEffect(PotionEffectType.SLOW, 60, 0);
	}

}
