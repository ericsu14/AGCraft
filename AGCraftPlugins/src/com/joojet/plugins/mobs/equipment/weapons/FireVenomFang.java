package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class FireVenomFang extends Equipment
{
	public FireVenomFang (ChatColor color)
	{
		super (EquipmentType.FIRE_VENOM_FANG, Material.STONE_SWORD, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		
		this.setDisplayName("Fire Venom Fang");
		this.addLoreToItemMeta("A fang stolen from one firey boi.");
	}
}
