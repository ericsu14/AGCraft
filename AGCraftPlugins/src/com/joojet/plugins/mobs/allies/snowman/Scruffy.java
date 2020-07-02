package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
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
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.WATER_BREATHING);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.REGEN);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Chestplate
		this.helmet = new ItemStack (Material.CARVED_PUMPKIN, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		helmetMeta.addEnchant(Enchantment.THORNS, 7, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Scruffy's Face");
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 12.0, 8.0, 0.5);
		this.helmet.setItemMeta(helmetMeta);
	}
		
}
