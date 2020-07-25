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
	/** Constructs a Potion with an item count of 1
	 * 		@param material - Type of material this item is made out of
	 * 		@param equipment - The equipment slot in which this item's stats are applied to
	 * 		@param color - ChatColor applied to this equipment's name and lore */
	public AbstractPotionEquipment (Material material, EquipmentSlot equipment, ChatColor color)
	{
		super (material, equipment, color);
		this.addPotionData();
	}
	
	/** Constructs a Potion with a custom item count
	 * 		@param material - Type of material this item is made out of
	 * 		@param equipment - The equipment slot in which this item's stats are applied to
	 * 		@param color - ChatColor applied to this equipment's name and lore
	 * 		@param count - Number of items that comes with this itemstack */
	public AbstractPotionEquipment (Material material, EquipmentSlot equipment, ChatColor color, int count)
	{
		super (material, equipment, color, count);
		this.addPotionData();
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
	
	/** Adds custom effects to this potion. Must be implemented by inherited classes */
	public abstract void addPotionData ();
}
