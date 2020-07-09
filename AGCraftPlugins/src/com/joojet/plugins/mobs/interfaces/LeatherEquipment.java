package com.joojet.plugins.mobs.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public abstract class LeatherEquipment extends Equipment 
{
	public LeatherEquipment(Material material, EquipmentSlot equipmentSlot, ChatColor chatColor) 
	{
		super(material, equipmentSlot, chatColor);
	}
	
	protected void setColor (Color color)
	{
		LeatherArmorMeta meta = (LeatherArmorMeta) this.getItemMeta();
		meta.setColor(color);
		this.setItemMeta(meta);
	}

}
