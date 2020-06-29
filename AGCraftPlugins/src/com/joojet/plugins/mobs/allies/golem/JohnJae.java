package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
		
		this.effects.add(CustomPotionEffect.RESISTANCE_II.getPotionEffect());
		this.effects.add(CustomPotionEffect.REGEN.getPotionEffect());
		this.effects.add(CustomPotionEffect.STRENGTH_II.getPotionEffect());
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.JUMP_BOOST.getPotionEffect());
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		
		this.health = 250;
		
		// Chestplate
		this.chestplate = new ItemStack (Material.IRON_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.THORNS, 3, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.setDisplayName(this.color + "The Pecks");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
	}
}
