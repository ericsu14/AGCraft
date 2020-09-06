package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;


public class EternalSpititOfTroy extends Equipment 
{
	public EternalSpititOfTroy ()
	{
		super (EquipmentTypes.ETERNAL_SPIRIT_OF_TROY, Material.BOW, EquipmentSlot.HAND, ChatColor.GOLD);
		this.setDisplayName(ChatColor.RED + "The" 
				+ ChatColor.GOLD + " Eternal"
				+ ChatColor.RED + " Spirit"
				+ ChatColor.GOLD + " of"
				+ ChatColor.RED + " Troy");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
	}
}
