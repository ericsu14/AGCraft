package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class DoomChest extends LeatherEquipment
{
	public DoomChest (ChatColor color)
	{
		super (Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addDefenseAttributes(8.0, 6.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.THORNS, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.setDisplayName("Chestplate of the Doom Slayer");
		this.addLoreToItemMeta("For he alone was the Hell Walker, the Unchained Predator, who sought retribution...");
		this.setColor(Color.fromRGB(134, 143, 50));
		this.makeUnbreakable();
	}
}
