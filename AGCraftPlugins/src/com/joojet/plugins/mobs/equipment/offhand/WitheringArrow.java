package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class WitheringArrow extends TippedArrow 
{
	public WitheringArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.BLACK);
		this.addPotionEffect(PotionEffectType.WITHER, 280, 140);
		this.addPotionEffect(PotionEffectType.HARM, 1, 1);
		this.setDisplayName("Withering Arrow");
		this.addLoreToItemMeta("Let your enemies wither away...");
	}
}
