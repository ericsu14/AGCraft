package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;


public class EternalTrojanSword extends Equipment 
{
	public EternalTrojanSword ()
	{
		super (EquipmentTypes.ETERNAL_TROJAN_SWORD, Material.IRON_SWORD, EquipmentSlot.HAND, ChatColor.RED);
		this.setDisplayName("Eternal"
				+ ChatColor.GOLD + " Golden"
				+ ChatColor.RED + " Sword");
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("#fighton forever!");
		this.addAttackAttributes(9.0, 1.6);
		this.addSpeedAttribute(0.05);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);
		this.makeSoulbound();
	}
}
