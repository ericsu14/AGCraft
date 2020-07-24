package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EnhancedLuckPotion extends PotionEquipment 
{
	public EnhancedLuckPotion (ChatColor color)
	{
		super (color);
		this.addPotionEffect(PotionEffectType.LUCK, 6000, 1);
		this.setDisplayName("Enhanced Potion of Luck");
		this.addLoreToItemMeta("Significantly increases loot table rewards for five minutes.");
		this.setColor(Color.fromRGB(130, 255, 211));
	}
}
