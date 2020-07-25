package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;


public class EnhancedHastePotion extends PotionEquipment
{
	public EnhancedHastePotion (ChatColor color)
	{
		super (color);
	}

	@Override
	public void addPotionData() 
	{
		this.addPotionEffect(PotionEffectType.FAST_DIGGING, 6000, 1);
		this.setDisplayName("Enhanced Potion of Haste");
		this.addLoreToItemMeta("Significantly increases mining speed for five minutes.");
		this.setColor(Color.fromRGB(235, 171, 52));
	}
	
	
}
