package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.TippedArrow;

public class WitheringArrow extends TippedArrow 
{
	public WitheringArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.BLACK);
		this.addCustomEffect(PotionEffectType.WITHER, 280, 1);
		this.addCustomEffect(PotionEffectType.HARM, 2, 1);
		this.setDisplayName("Withering Arrow");
		this.addLoreToItemMeta("Let your enemies wither away...");
	}
}
