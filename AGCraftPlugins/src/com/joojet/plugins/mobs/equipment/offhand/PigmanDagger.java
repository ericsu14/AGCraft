package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PigmanDagger extends Equipment
{
	public PigmanDagger (ChatColor color)
	{
		super (EquipmentType.PIGMAN_DAGGER, Material.GOLDEN_SWORD, EquipmentSlot.OFF_HAND, color);
		this.setDisplayName("Pigman Dagger");
		this.addLoreToItemMeta("Held by those who mastered the art of Akimbo. Hold this in your offhand to deal increased damage.");
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addAttackAttributes(4.0, 0.5);
	}
}
