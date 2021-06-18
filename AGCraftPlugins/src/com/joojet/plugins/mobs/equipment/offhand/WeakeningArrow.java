package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class WeakeningArrow extends TippedArrow 
{
	public WeakeningArrow (ChatColor color)
	{
		super (EquipmentType.WEAKENING_ARROW, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setColor(Color.GRAY);
		this.addPotionEffect(PotionEffectType.SLOW, 100, 0);
		this.setDisplayName("Take it #agslow");
		this.addLoreToItemMeta("Slows down the enemy for a short amount of time.");
	}
}
