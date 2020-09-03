package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class SpiritualFantasy extends Equipment
{
	public SpiritualFantasy (ChatColor color)
	{
		super (EquipmentTypes.SPIRITUAL_FANTASY, Material.BOW, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		this.setDisplayName("A Spiritual Fantasy");
		this.addLoreToItemMeta("Don't even think about it.");
	}
}
