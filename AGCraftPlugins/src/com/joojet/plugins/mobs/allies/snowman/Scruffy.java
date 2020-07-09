package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.head.ScruffyFace;
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
		this.helmet = new ScruffyFace (this.color);
		
		// Boots
		this.boots = new LetItGo (this.color);
	}
		
}
