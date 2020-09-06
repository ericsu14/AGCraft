package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ATerribleFate extends Equipment
{
	public ATerribleFate (ChatColor color)
	{
		super (EquipmentTypes.A_TERRIBLE_FATE, Material.BOW, EquipmentSlot.HAND, color);
		this.setDisplayName("A Terrible Fate");
		
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 7);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
		
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.makeSoulbound();
	}
}
