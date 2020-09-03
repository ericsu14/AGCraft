package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BruinSword extends Equipment
{
	public BruinSword (ChatColor color)
	{
		super (EquipmentTypes.BRUIN_SWORD, Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("Bruin Sword");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addAttackAttributes(6.0, 1.6);
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("The Bruins are up to no good. This weapons just proves it.");
	}
}
