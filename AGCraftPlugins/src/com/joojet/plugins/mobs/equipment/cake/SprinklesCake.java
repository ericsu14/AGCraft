package com.joojet.plugins.mobs.equipment.cake;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;


public class SprinklesCake extends Equipment
{
	public SprinklesCake ()
	{
		super (EquipmentTypes.SPRINKLES_CAKE, Material.CAKE, EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		this.setDisplayName(StringUtil.alternateTextColors("sprinkle's Birthday Cake", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD));
		
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("A cake created by jooj to celebrate someone's special day!");
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
		this.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		this.makeSoulbound();
	}
}
