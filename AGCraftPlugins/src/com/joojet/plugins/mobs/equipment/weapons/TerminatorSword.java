package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TerminatorSword extends Equipment
{
	public TerminatorSword (ChatColor color)
	{
		super (EquipmentTypes.TERMINATOR_SWORD, Material.GOLDEN_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("The Sword of the Terminator");
		this.loreColor = ChatColor.DARK_RED;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("I will be back... with weapons...");
		this.addAttackAttributes(9.0, 1.6);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 4);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		this.makeUnbreakable();
		this.makeSoulbound();
	}
}
