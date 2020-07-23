package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class PerpetualTorment extends Equipment
{
	public PerpetualTorment (ChatColor color)
	{
		super (Material.NETHERITE_SWORD, EquipmentSlot.OFF_HAND, color);
		this.setDisplayName("Perpetual Torment");
		this.addLoreToItemMeta("With boiling blood, he scoured the Umbral Plains seeking vengeance against the dark lords who had wronged him. ");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
	}
}