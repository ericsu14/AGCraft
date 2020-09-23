package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class TrojanSword extends Equipment
{
	public TrojanSword (ChatColor color)
	{
		super (EquipmentType.TROJAN_SWORD, Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName(USCFaction.generateUSCDisplayName("Trojan Sword"));
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
