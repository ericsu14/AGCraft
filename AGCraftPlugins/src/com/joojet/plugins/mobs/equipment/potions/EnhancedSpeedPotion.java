package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EnhancedSpeedPotion extends PotionEquipment {
	public EnhancedSpeedPotion (ChatColor color)
	{
		super (color);
		this.addPotionEffect(PotionEffectType.SPEED, 4800, 1);
		this.setDisplayName("Enhanced Potion of Speed");
		this.addLoreToItemMeta("Significantly increases movement speed for four minutes.");
		this.setColor(Color.fromRGB(137, 207, 240));
	}
}
