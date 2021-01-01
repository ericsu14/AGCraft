package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class DoomBow extends Equipment {

	public DoomBow (ChatColor chatColor) 
	{
		super(EquipmentType.DOOM_BOW, Material.BOW, EquipmentSlot.HAND, chatColor);
		this.setDisplayName("Doom Bow");
		
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 8);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		
		this.addLoreToItemMeta("Rip... and TEAR!!");
		
		this.makeSoulbound();
	}
	
}
