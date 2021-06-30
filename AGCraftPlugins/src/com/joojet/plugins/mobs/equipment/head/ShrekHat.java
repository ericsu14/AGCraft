package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ShrekHat extends Equipment
{
	public ShrekHat (ChatColor color)
	{
		super (EquipmentType.SHREK_HAT, PlayerHead.SHREK, color);
		this.setDisplayName("Shrek is Love, Shrek is Life");
		this.addLoreToItemMeta("Shhhhh, it's all ogre now...");
		this.addDefenseAttributes(2.0, 2.0, 0.10);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
	}
}
