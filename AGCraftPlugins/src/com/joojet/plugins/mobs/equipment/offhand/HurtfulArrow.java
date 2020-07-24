package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class HurtfulArrow extends TippedArrow {
	public HurtfulArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.BLACK);
		this.addPotionEffect(PotionEffectType.HARM, 1, 0);
		this.setDisplayName("Hurtful Arrow");
		this.addLoreToItemMeta("Inflicts great pain to your enemies");
	}
}
