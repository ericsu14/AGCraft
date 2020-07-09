package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class VeryPotentBow extends Equipment
{
	public VeryPotentBow (ChatColor color)
	{
		super (Material.BOW, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		this.setDisplayName("Very Potent Bow");
		this.addLoreToItemMeta("This bow brings many pain. Pain is bad.");
	}
}
