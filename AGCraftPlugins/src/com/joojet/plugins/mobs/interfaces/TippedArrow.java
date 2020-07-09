package com.joojet.plugins.mobs.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class TippedArrow extends Equipment 
{
	public TippedArrow (ChatColor color)
	{
		super (Material.TIPPED_ARROW, EquipmentSlot.OFF_HAND, color);
	}
	
	/** Adds a potion effect to the tipped arrow */
	protected void addCustomEffect (PotionEffectType type, int duration, int amplifier)
	{
		PotionMeta tippedArrow = (PotionMeta) this.getItemMeta();
		tippedArrow.addCustomEffect(new PotionEffect (type, duration, amplifier), true);
		this.setItemMeta(tippedArrow);
	}
	
	/** Changes the color of the tipped arrow */
	protected void setColor (Color color)
	{
		PotionMeta tippedArrow = (PotionMeta) this.getItemMeta();
		tippedArrow.setColor(color);
		this.setItemMeta(tippedArrow);
	}
}
