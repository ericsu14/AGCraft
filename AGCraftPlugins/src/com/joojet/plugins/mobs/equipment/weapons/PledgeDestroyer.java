package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PledgeDestroyer extends Equipment 
{

	public PledgeDestroyer(ChatColor chatColor) 
	{
		super(EquipmentType.PLEDGE_DESTROYER, Material.BOW, EquipmentSlot.HAND, chatColor);
		this.setDisplayName(StringUtil.alternateTextColors("The Pledge Destroyer", TextPattern.WORD, 
				ChatColor.BLUE, ChatColor.GOLD, ChatColor.YELLOW));
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("Some pledges are about to be clapped tonight...");
	}

}
