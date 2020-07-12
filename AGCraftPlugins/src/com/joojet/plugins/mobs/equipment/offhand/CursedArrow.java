package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.TippedArrow;

public class CursedArrow extends TippedArrow
{
	public CursedArrow (ChatColor color)
	{
		super (color);
		this.setDisplayName("Cursed Arrow");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.addCustomEffect(PotionEffectType.BLINDNESS, 70, 0);
		this.addCustomEffect(PotionEffectType.POISON, 70, 3);
		this.addCustomEffect(PotionEffectType.CONFUSION, 150, 1);
		this.setColor(Color.fromRGB(173, 61, 24));
	}
}
