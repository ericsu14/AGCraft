package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.TippedArrow;

public class ThanosArrow extends TippedArrow 
{

	public ThanosArrow(ChatColor color) 
	{
		super(color);
		this.setColor(Color.fromRGB(75, 0, 130));
		this.addCustomEffect(PotionEffectType.WITHER, 70, 2);
		this.addCustomEffect(PotionEffectType.HARM, 1, 1);
		this.setDisplayName("Thanos Arrow");
		this.addLoreToItemMeta("Will break the enemy's health in half...");
	}

}
