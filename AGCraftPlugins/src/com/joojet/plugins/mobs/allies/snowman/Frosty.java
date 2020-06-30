package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Frosty extends MobEquipment
{
	public Frosty ()
	{
		this.name = "Frosty the Snowman";
		this.color = ChatColor.AQUA;
		this.health = 30;
		this.showName = true;
		
		this.effects.add(CustomPotionEffect.RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.WATER_BREATHING.getPotionEffect());
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.REGEN.getPotionEffect());
		
		// Chestplate
		this.helmet = new ItemStack (Material.CARVED_PUMPKIN, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		helmetMeta.addEnchant(Enchantment.THORNS, 4, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Frosty's Face");
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 8.0, 4.0, 0.2);
		this.helmet.setItemMeta(helmetMeta);
	}
}
