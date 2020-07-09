package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class BulletproofIronChestplate extends Equipment
{
	public BulletproofIronChestplate (ChatColor color)
	{
		super (Material.IRON_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
		this.setDisplayName("Bulletproof Iron Chestplate");
		this.addLoreToItemMeta("Reinforced with titanium to have better resistance towards high damaging attacks.");
		this.addDefenseAttributes(7.0, 2.0, 0.0);
	}
}
