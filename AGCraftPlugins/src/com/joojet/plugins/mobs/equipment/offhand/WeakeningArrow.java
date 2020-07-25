package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class WeakeningArrow extends TippedArrow 
{
	public WeakeningArrow (ChatColor color)
	{
		super (color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setColor(Color.GRAY);
		this.addPotionEffect(PotionEffectType.WEAKNESS, 70, 0);
		this.setDisplayName("Weakening Arrow");
		this.addLoreToItemMeta("Weakens the enemy for a short amount of time.");
	}
}
