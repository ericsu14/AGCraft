package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.TippedArrow;

public class HurtfulArrow extends TippedArrow {
	public HurtfulArrow (ChatColor color)
	{
		super (color);
		this.setColor(Color.BLACK);
		this.addCustomEffect(PotionEffectType.HARM, 1, 0);
		this.setDisplayName("Hurtful Arrow");
		this.addLoreToItemMeta("Inflicts great pain to your enemies");
	}
}
