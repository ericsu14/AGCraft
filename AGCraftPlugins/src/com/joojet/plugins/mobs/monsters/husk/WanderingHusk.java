package com.joojet.plugins.mobs.monsters.husk;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WanderingHusk extends MobEquipment
{
	public WanderingHusk ()
	{
		this.name = "Wandering Husk";
		this.color = ChatColor.GREEN;
		this.health = 30;
		
		this.effects.add(CustomPotionEffect.STRENGTH.getPotionEffect());
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		
		this.boots = new ItemStack (Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta  bootMeta = (LeatherArmorMeta ) this.boots.getItemMeta();
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.10);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 4.0, 1.0, 0.1);
		bootMeta.setDisplayName(this.color + "Desert Sandals");
		ArrayList <String> bootLore = new ArrayList <String> ();
		bootLore.add (this.color + "Passed down by generations,");
		bootLore.add (this.color + "these sandals are used");
		bootLore.add (this.color + "to walk across this");
		bootLore.add (this.color + "scorched earth safely.");
		bootMeta.setLore(bootLore);
		bootMeta.setColor(Color.fromRGB(237, 201, 175));
		this.boots.setItemMeta(bootMeta);
	}
}
