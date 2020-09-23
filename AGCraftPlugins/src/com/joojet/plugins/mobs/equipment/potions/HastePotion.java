package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class HastePotion extends PotionEquipment 
{
	public HastePotion (ChatColor color)
	{
		super (EquipmentType.HASTE_POTION, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.addPotionEffect(PotionEffectType.FAST_DIGGING, 9600, 0);
		this.setDisplayName("Potion of Haste");
		this.addLoreToItemMeta("Slightly increases mining speed for eight minutes.");
		this.setColor(Color.YELLOW);
	}
}
