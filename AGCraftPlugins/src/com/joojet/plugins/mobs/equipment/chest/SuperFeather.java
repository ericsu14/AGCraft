package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class SuperFeather extends Equipment 
{
	public SuperFeather ()
	{
		super (EquipmentType.SUPER_FEATHER, Material.FEATHER, EquipmentSlot.CHEST, ChatColor.GOLD);
		this.setDisplayName(StringUtil.alternateTextColors("Super Feather", TextPattern.WORD, 
				ChatColor.WHITE, ChatColor.YELLOW));
		this.addUnsafeEnchantment(Enchantment.THORNS, 5);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addSpeedAttribute(0.15);
		this.addAttackAttributes(2.0, 0.0);
		this.addDefenseAttributes(4.0, 8.0, 0.1);
		this.addLoreToItemMeta("Every super chicken requires its own coat of armor.");
		this.makeSoulbound();
	}
}
