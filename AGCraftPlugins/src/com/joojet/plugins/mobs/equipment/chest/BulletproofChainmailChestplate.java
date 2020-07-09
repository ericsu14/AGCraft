package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class BulletproofChainmailChestplate extends Equipment
{
	public BulletproofChainmailChestplate (ChatColor color)
	{
		super (Material.CHAINMAIL_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setDisplayName("Reinforced Chainmail Helmet");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addDefenseAttributes(6.0, 1.5, 0.1);
		this.addLoreToItemMeta("Reinforced with stainless steel to have better resistance towards projectiles.");
	}
}
