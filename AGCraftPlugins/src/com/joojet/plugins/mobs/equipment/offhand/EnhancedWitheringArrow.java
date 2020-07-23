package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.TippedArrow;

public class EnhancedWitheringArrow extends TippedArrow
{
	public EnhancedWitheringArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.fromRGB(75, 0, 130));
		this.addCustomEffect(PotionEffectType.WITHER, 280, 2);
		this.addCustomEffect(PotionEffectType.HARM, 1, 3);
		this.setDisplayName("Enhanced Withering Arrow");
		this.addLoreToItemMeta("Let your enemies wither away in complete misery...");
	}
}
