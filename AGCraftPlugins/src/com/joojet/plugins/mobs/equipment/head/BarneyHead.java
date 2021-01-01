package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BarneyHead extends Equipment
{
	public BarneyHead (ChatColor color)
	{
		super (EquipmentType.BARNEY_HEAD, PlayerHead.BARNEY, color);
		this.setDisplayName("Barney the Dinosaur");
		this.addLoreToItemMeta("now cha cha real smooth...");
		this.addHealthAttributes(8.0);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		this.addDefenseAttributes(2.0, 4.0, 0.15);
		this.addAttackAttributes(2.0, 0.0);
		this.makeSoulbound();
	}
}
