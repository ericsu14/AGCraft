package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class DoomGuyLegs extends LeatherEquipment
{
	public DoomGuyLegs (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.wordsPerLine = 8;
		this.loreColor = ChatColor.RED;
		this.setDisplayName("Leggings of the Doom Slayer");
		this.addDefenseAttributes(6.0, 5.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.setColor(Color.fromRGB(140, 143, 62));
		this.addLoreToItemMeta("None could stand before the horde but the Doom Slayer...");
		this.makeUnbreakable();
	}
}
