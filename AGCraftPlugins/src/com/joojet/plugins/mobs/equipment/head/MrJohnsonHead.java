package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class MrJohnsonHead extends Equipment
{

	public MrJohnsonHead(ChatColor color) 
	{
		super(EquipmentType.MR_JOHNSON_HEAD, PlayerHead.MR_JOHNSON, color);
		this.addDefenseAttributes(3.0, 3.0, 0.10);
		this.addUnsafeEnchantment (Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.THORNS, 2);
		this.setDisplayName("Mr. Johnson's Head");
		this.addLoreToItemMeta("Mr. Johnson wants to clap some pledges tonight.");
	}

}
