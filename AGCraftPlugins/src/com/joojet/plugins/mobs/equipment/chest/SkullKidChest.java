package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.LeatherEquipment;

public class SkullKidChest extends LeatherEquipment
{
	public SkullKidChest (ChatColor color)
	{
		super (Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setDisplayName("?????");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.setColor(Color.fromRGB(177, 163, 38));
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addDefenseAttributes(8.0, 4.0, 0.20);
	}
}
