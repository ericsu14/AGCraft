package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class USCSpikedBoots extends LeatherEquipment
{
	public USCSpikedBoots (ChatColor color)
	{
		super (EquipmentTypes.USC_SPIKED_BOOTS, Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Spiky" + ChatColor.RED + " Boots");
		this.addDefenseAttributes(3.0, 1.0, 0.07);
		this.addSpeedAttribute(0.20);
		this.makeUnbreakable();
		this.addAttackAttributes(2.0, 0.1);
		this.setColor(Color.BLACK);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("Reinforced with spikes for better grip on the fields. Packs quite a punch too!");
	}
}
