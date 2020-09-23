package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class DoomBlade extends Equipment
{
	public DoomBlade (ChatColor color)
	{
		super (EquipmentType.DOOM_BLADE, Material.NETHERITE_SWORD, EquipmentSlot.HAND, color);
		this.addAttackAttributes(13.0, 2.0);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 5);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.setDisplayName("Doom Blade");
		this.addLoreToItemMeta("Rip... and tear!");
		this.makeSoulbound();
	}
}
