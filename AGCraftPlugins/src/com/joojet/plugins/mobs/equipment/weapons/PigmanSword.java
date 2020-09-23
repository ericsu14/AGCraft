package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PigmanSword extends Equipment 
{
	public PigmanSword (ChatColor color)
	{
		super (EquipmentType.PIGMAN_SWORD, Material.GOLDEN_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("Pigmen Sword");
		this.addLoreToItemMeta("Can only be weilded by the wisest of all Pigmen...");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addAttackAttributes(6.0, 1.6);
	}
}
