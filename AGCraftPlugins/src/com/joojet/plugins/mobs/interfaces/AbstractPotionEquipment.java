package com.joojet.plugins.mobs.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.equipment.Equipment;

public abstract class AbstractPotionEquipment extends Equipment 
{
	public AbstractPotionEquipment (Material material, EquipmentSlot equipment, ChatColor color)
	{
		super (material, equipment, color);
	}
	
	/** Changes the base color of this custom potion to a new color */
	public void setColor (Color color)
	{
		PotionMeta potMeta = (PotionMeta) this.getItemMeta();
		potMeta.setColor(color);
		this.setItemMeta(potMeta);
	}
	
	/** Adds a new custom potion effect to this potion */
	public void addPotionEffect (PotionEffectType effect, int duration, int amplifier)
	{
		PotionMeta potMeta = (PotionMeta) this.getItemMeta();
		potMeta.addCustomEffect(new PotionEffect (effect, duration, amplifier), false);
		this.setItemMeta(potMeta);
	}
}