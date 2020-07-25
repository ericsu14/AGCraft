package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class LuckPotion extends PotionEquipment 
{
	public LuckPotion (ChatColor color)
	{
		super (color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.addPotionEffect(PotionEffectType.LUCK, 9600, 0);
		this.setDisplayName("Potion of Luck");
		this.addLoreToItemMeta("Slightly increases loot table rewards for eight minutes.");
		this.setColor(Color.LIME);
	}
}
