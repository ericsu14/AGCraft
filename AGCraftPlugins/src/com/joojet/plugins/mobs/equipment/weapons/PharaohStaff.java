package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PharaohStaff extends Equipment
{
	public PharaohStaff (ChatColor color)
	{
		super (EquipmentType.PHARAOH_STAFF, Material.GOLDEN_HOE, EquipmentSlot.HAND, color);
		this.addAttackAttributes(6.0, 1.4);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.setDisplayName("Pharaoh's Royal Staff");
		this.addLoreToItemMeta("They say you don't waste gold on a hoe.");
	}
}
