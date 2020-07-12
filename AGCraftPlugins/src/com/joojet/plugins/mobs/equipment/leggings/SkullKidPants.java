package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.LeatherEquipment;

public class SkullKidPants extends LeatherEquipment
{
	public SkullKidPants (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName("?????");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.addDefenseAttributes(6.0, 4.0, 0.10);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.setColor(Color.fromRGB(173, 61, 24));
	}
}
