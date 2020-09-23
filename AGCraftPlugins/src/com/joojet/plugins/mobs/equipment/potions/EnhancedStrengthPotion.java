package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class EnhancedStrengthPotion extends PotionEquipment
{
	public EnhancedStrengthPotion (ChatColor color)
	{
		super (EquipmentType.EHNAHCNED_STRENGTH_POTION, color);
	}
	
	@Override
	public void addPotionData ()
	{
		this.addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 1);
		this.setDisplayName("Enhanced Potion of Strength");
		this.addLoreToItemMeta("Significantly increases attack damage for five minutes.");
		this.setColor(Color.MAROON);
	}
}
