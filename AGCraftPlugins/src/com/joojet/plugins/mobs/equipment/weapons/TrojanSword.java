package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class TrojanSword extends Equipment
{
	public TrojanSword (ChatColor color)
	{
		super (Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Trojan" + ChatColor.RED + " Sword");
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 7;
		this.addLoreToItemMeta("Tommy trojan once weilded this blade to defeat his sworn enemies. Now you will carry on his legacy and uphold the traditions of this university!");
		this.addAttackAttributes(8.0, 1.6);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 3);
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
	}
}
