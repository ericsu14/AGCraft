package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.TippedArrow;

public class WeakeningArrow extends TippedArrow 
{
	public WeakeningArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.GRAY);
		this.addCustomEffect(PotionEffectType.WEAKNESS, 70, 0);
		this.setDisplayName("Weakening Arrow");
		this.addLoreToItemMeta("Weakens the enemy for a short amount of time.");
	}
}
