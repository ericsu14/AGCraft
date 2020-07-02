package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class JohnJae extends MobEquipment
{
	public JohnJae ()
	{
		this.name = "John Jae";
		this.color = ChatColor.GOLD;
		this.showName = true;

		this.addPotionEffect(CustomPotionEffect.RESISTANCE_II);
		this.addPotionEffect(CustomPotionEffect.REGEN);
		this.addPotionEffect(CustomPotionEffect.STRENGTH_II);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.JUMP_BOOST);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.health = 250;
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.THORNS, 3, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.setDisplayName(this.color + "The Pecks");
		this.chestplate.setItemMeta(chestMeta);
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 10.0, 5.0, 0.50);
		this.addRandomDamage(this.chestplate);
	}
}
