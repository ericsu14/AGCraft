package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EnhancedSpeedPotion extends PotionEquipment {
	public EnhancedSpeedPotion (ChatColor color)
	{
		super (color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.addPotionEffect(PotionEffectType.SPEED, 6000, 1);
		this.setDisplayName("Enhanced Potion of Speed");
		this.addLoreToItemMeta("Significantly increases movement speed for five minutes.");
		this.setColor(Color.fromRGB(137, 207, 240));
	}
}
