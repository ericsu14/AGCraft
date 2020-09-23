package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;


public class EternalTrojanSword extends Equipment 
{
	public EternalTrojanSword ()
	{
		super (EquipmentType.ETERNAL_TROJAN_SWORD, Material.IRON_SWORD, EquipmentSlot.HAND, ChatColor.RED);
		this.setDisplayName(StringUtil.alternateTextColors("Eternal Trojan Sword", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD));
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("Tommy trojan once weilded this blade to defeat his sworn enemies. Now you will carry on his legacy and uphold the traditions of this university!");
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
