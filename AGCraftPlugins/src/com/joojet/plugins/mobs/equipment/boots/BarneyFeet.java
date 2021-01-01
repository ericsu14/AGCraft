package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class BarneyFeet extends LeatherEquipment
{
	public BarneyFeet (ChatColor color)
	{
		super (EquipmentType.BARNEY_FEET, Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.addSpeedAttribute(0.20);
		this.addDefenseAttributes(4.0, 1.0, 0.05);
		this.addAttackAttributes(1.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 4);
		this.setDisplayName("Barney's Wacky Feet");
		this.addLoreToItemMeta("I'm gonna stick this foot deep inside of uranus.");
		this.setColor(Color.fromRGB(182, 38, 132));
		this.makeUnbreakable();
		this.makeSoulbound();
	}
}
