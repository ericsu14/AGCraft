package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EnhancedWitheringArrow extends TippedArrow
{
	public EnhancedWitheringArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.fromRGB(75, 0, 130));
		this.addPotionEffect(PotionEffectType.WITHER, 280, 2);
		this.addPotionEffect(PotionEffectType.HARM, 1, 3);
		this.setDisplayName("Enhanced Withering Arrow");
		this.addLoreToItemMeta("Let your enemies wither away in complete misery...");
	}
}
