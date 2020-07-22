package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.PotionEquipment;

public class HastePotion extends PotionEquipment 
{
	public HastePotion (ChatColor color)
	{
		super (color);
		this.addPotionEffect(PotionEffectType.FAST_DIGGING, 9600, 0);
		this.setDisplayName("Potion of Haste");
		this.addLoreToItemMeta("Slightly increases mining speed for eight minutes.");
		this.setColor(Color.YELLOW);
	}
}
