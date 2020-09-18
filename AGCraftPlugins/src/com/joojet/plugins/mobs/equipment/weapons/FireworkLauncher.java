package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class FireworkLauncher extends Equipment 
{
	public FireworkLauncher (ChatColor color)
	{
		super (EquipmentTypes.FIREWORK_LAUNCHER, Material.CROSSBOW, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.setDisplayName("Firework Launcher");
		this.addLoreToItemMeta(StringUtil.alternateTextColors("Light up the sky!", TextPattern.CHARACTER, ChatColor.RED,
				ChatColor.WHITE, ChatColor.BLUE));
	}
}
