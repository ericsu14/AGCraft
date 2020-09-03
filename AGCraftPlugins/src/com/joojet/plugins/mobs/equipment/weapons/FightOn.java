package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class FightOn extends Equipment
{
	public FightOn (ChatColor color)
	{
		super (EquipmentTypes.FIGHT_ON, Material.BOW, EquipmentSlot.HAND, color);
		this.setDisplayName(ChatColor.RED + "Fight" 
				+ ChatColor.GOLD + " On!");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
	}
}
