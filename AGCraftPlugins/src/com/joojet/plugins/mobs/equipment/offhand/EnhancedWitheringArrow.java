package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EnhancedWitheringArrow extends TippedArrow
{
	public EnhancedWitheringArrow (ChatColor color)
	{
		super (color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setColor(Color.fromRGB(75, 0, 130));
		this.addPotionEffect(PotionEffectType.WITHER, 200, 2);
		this.addPotionEffect(PotionEffectType.HARM, 1, 2);
		this.setDisplayName("Enhanced Withering Arrow");
		this.addLoreToItemMeta("Let your enemies wither away in complete misery...");
	}
}
