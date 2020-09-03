package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class BarneyChest extends LeatherEquipment
{
	public BarneyChest (ChatColor color)
	{
		super (EquipmentTypes.BARNEY_CHEST, Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setDisplayName("Barney's Glorious Pecks");
		this.addLoreToItemMeta("I'm gonna run you over with my train.");
		this.addUnsafeEnchantment(Enchantment.THORNS, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		this.addDefenseAttributes(7.0, 5.0, 0.15);
		this.setColor(Color.fromRGB(0, 152, 73));
		this.makeUnbreakable();
	}
}
