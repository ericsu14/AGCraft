package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class FireworkLauncher extends Equipment 
{
	public FireworkLauncher (ChatColor color)
	{
		super (Material.CROSSBOW, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.setDisplayName(ChatColor.GOLD + "Firework Launcher");
		this.addLoreToItemMeta(this.americanizeText("Light up the sky!"));
	}
}
