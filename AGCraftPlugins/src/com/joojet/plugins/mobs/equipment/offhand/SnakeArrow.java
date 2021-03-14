package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class SnakeArrow extends TippedArrow {

	public SnakeArrow(ChatColor color) 
	{
		super(EquipmentType.SNAKE_ARROW, color);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName("Snake Arrow");
		this.setColor(Color.fromRGB(17, 41, 89));
	}

}
