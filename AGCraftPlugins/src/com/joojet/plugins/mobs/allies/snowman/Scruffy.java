package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Scruffy extends MobEquipment
{
	public Scruffy ()
	{
		this.name = "Scruffy";
		this.color = ChatColor.GOLD;
		this.health = 40;
		
		this.effects.add(CustomPotionEffect.RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.WATER_BREATHING.getPotionEffect());
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.REGEN.getPotionEffect());
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		
		// Chestplate
		this.helmet = new ItemStack (Material.CARVED_PUMPKIN, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		helmetMeta.addEnchant(Enchantment.THORNS, 4, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Scruffy's Face");
		this.helmet.setItemMeta(helmetMeta);
	}
		
}
