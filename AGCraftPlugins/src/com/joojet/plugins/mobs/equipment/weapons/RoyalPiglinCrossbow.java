package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class RoyalPiglinCrossbow extends Equipment
{
	public RoyalPiglinCrossbow (ChatColor color)
	{
		super (EquipmentType.ROYAL_PIGLIN_CROSSBOW, Material.CROSSBOW, EquipmentSlot.HAND, color);
		this.setDisplayName("Royal Piglin Crossbow");
		this.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
		this.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 4);
		this.addSpeedAttribute(0.05);
		this.addLoreToItemMeta("A crossbow used by a legendary piglin. Over 9000 hogs were slain by this crossbow.");
		this.makeUnbreakable();
		this.makeSoulbound();
	}
}
