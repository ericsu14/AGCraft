package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class OgreAxe extends Equipment
{
	public OgreAxe (ChatColor color)
	{
		super (Material.WOODEN_AXE, EquipmentSlot.HAND, color);
		this.setDisplayName("Its All Ogre Now");
		this.addLoreToItemMeta("Shrek is love, shrek is life.");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
	}
}
