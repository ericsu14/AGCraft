package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class FightOn extends Equipment
{
	public FightOn (ChatColor color)
	{
		super (EquipmentType.FIGHT_ON, Material.BOW, EquipmentSlot.HAND, color);
		this.setDisplayName(ChatColor.RED + "Fight" 
				+ ChatColor.GOLD + " On!");
		this.addLoreToItemMeta("Show off your trojan spirit!");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
	}
}
