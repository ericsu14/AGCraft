package com.joojet.plugins.mobs.equipment;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public abstract class AbstractPotionEquipment extends Equipment 
{
	/** Constructs a Potion with an item count of 1
	 * 		@param equipmentType - Type of custom equipment this is
	 * 		@param material - Type of material this item is made out of
	 * 		@param equipment - The equipment slot in which this item's stats are applied to
	 * 		@param color - ChatColor applied to this equipment's name and lore */
	public AbstractPotionEquipment (EquipmentType equipmentType, Material material, EquipmentSlot equipment, ChatColor color)
	{
		super (equipmentType, material, equipment, color);
		this.addPotionData();
	}
	
	/** Constructs a Potion with a custom item count
	 * 		@param equipmentType - Type of custom equipment this is
	 * 		@param material - Type of material this item is made out of
	 * 		@param equipment - The equipment slot in which this item's stats are applied to
	 * 		@param color - ChatColor applied to this equipment's name and lore
	 * 		@param count - Number of items that comes with this itemstack */
	public AbstractPotionEquipment (EquipmentType equipmentType, Material material, EquipmentSlot equipment, ChatColor color, int count)
	{
		super (equipmentType, material, equipment, color, count);
		this.addPotionData();
	}
	
	/** Changes the base color of this custom potion to a new color */
	protected void setColor (Color color)
	{
		PotionMeta potMeta = (PotionMeta) this.getItemMeta();
		potMeta.setColor(color);
		this.setItemMeta(potMeta);
	}
	
	/** Adds a new custom potion effect to this potion */
	protected void addPotionEffect (PotionEffectType effect, int duration, int amplifier)
	{
		PotionMeta potMeta = (PotionMeta) this.getItemMeta();
		potMeta.addCustomEffect(new PotionEffect (effect, duration, amplifier), true);
		this.setItemMeta(potMeta);
	}
	
	/** Adds custom effects to this potion. Must be implemented by inherited classes */
	protected abstract void addPotionData ();
}
