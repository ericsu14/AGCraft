package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EnhancedStrengthPotion extends PotionEquipment
{
	public EnhancedStrengthPotion (ChatColor color)
	{
		super (color);
		this.addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 4800, 1);
		this.setDisplayName("Enhanced Potion of Strength");
		this.addLoreToItemMeta("Significantly increases attack damage for four minutes.");
		this.setColor(Color.MAROON);
	}
}
