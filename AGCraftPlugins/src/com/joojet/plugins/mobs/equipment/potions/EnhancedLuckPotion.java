package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class EnhancedLuckPotion extends PotionEquipment 
{
	public EnhancedLuckPotion (ChatColor color)
	{
		super (EquipmentType.ENHANCED_LUCK_POTION, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.addPotionEffect(PotionEffectType.LUCK, 6000, 1);
		this.setDisplayName("Enhanced Potion of Luck");
		this.addLoreToItemMeta("Significantly increases loot table rewards for five minutes.");
		this.setColor(Color.fromRGB(130, 255, 211));
	}
}
