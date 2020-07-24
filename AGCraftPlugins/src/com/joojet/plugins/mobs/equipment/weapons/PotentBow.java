package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class PotentBow extends Equipment
{
	public PotentBow (ChatColor color)
	{
		super (Material.BOW, EquipmentSlot.HAND, color);
		this.setDisplayName("Potent Bow");
		this.addLoreToItemMeta("Guaranteed to ruin your day.");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
	}
}
