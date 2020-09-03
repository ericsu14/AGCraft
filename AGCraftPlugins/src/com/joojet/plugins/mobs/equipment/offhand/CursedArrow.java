package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentTypes;

public class CursedArrow extends TippedArrow
{
	public CursedArrow (ChatColor color)
	{
		super (EquipmentTypes.CURSED_ARROW, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setDisplayName("Cursed Arrow");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.addPotionEffect(PotionEffectType.BLINDNESS, 70, 0);
		this.addPotionEffect(PotionEffectType.POISON, 160, 3);
		this.addPotionEffect(PotionEffectType.CONFUSION, 150, 1);
		this.addPotionEffect(PotionEffectType.HARM, 1, 2);
		this.setColor(Color.fromRGB(173, 61, 24));
	}
}
