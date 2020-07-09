package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class SpiritualTravesty extends Equipment 
{
	public SpiritualTravesty (ChatColor color)
	{
		super (Material.NETHERITE_AXE, EquipmentSlot.HAND, color);
		this.addAttackAttributes(11.0, 3.0);
		
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		
		// Weapon name and lore
		this.setDisplayName("A Spiritual Travesty");
		this.addLoreToItemMeta("Forged from Glorious Nippon Steel, folded over 9000 times");
	}
}