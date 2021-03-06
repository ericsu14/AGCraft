package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class CyborgPigmanHead extends Equipment
{
	public CyborgPigmanHead (ChatColor color)
	{
		super (EquipmentType.CYBORG_PIGMAN_HEAD, PlayerHead.CYBORG_PIGMEN, color);
		this.setDisplayName("Head of the Terminator");
		this.addLoreToItemMeta("I will be back...");
		
		this.addDefenseAttributes(3.0, 4.0, 0.25);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
		this.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		this.addHealthAttributes(8.0);
	}
}
