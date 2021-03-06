package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class AGSpotted extends Equipment
{
	public AGSpotted (ChatColor color)
	{
		super (EquipmentType.AGSPOTTED, PlayerHead.CAMERA_HEAD, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addLoreToItemMeta("Used for stalker pics and spottings.");
		this.setDisplayName("#agspotted Camera");
		this.addDefenseAttributes(3.0, 3.0, 0.15);
	}
}
