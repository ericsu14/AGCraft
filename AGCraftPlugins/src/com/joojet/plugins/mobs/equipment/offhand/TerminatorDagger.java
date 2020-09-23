package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TerminatorDagger extends Equipment
{
	public TerminatorDagger (ChatColor color)
	{
		super (EquipmentType.TERMINATOR_DAGGER, Material.IRON_SWORD, EquipmentSlot.OFF_HAND, color);
		this.setDisplayName("Terminator Dagger");
		this.wordsPerLine = 6;
		this.chatColor = ChatColor.DARK_RED;
		this.addLoreToItemMeta("Weilded by those who are legendary masters of akimbo. Hold in your offhand to deal great damage.");
		this.addAttackAttributes(6.0, 0.3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.makeSoulbound();
		this.makeUnbreakable();
	}
}
