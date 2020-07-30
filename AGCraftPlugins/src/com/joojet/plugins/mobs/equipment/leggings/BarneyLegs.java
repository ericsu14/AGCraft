package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class BarneyLegs extends LeatherEquipment
{
	public BarneyLegs (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName("Barney's Meaty Legs");
		this.addLoreToItemMeta("He never skips leg day.");
		this.addDefenseAttributes(5.0, 2.5, 0.05);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		this.setColor(Color.fromRGB(182, 38, 132));
		this.makeUnbreakable();
	}
}
