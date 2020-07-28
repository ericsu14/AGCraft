package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class StrawberryMocktail extends PotionEquipment
{
	public StrawberryMocktail (ChatColor color)
	{
		super (color);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName("Strawberry Mocktail");
		this.addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 36000, 1);
		this.addPotionEffect(PotionEffectType.SPEED, 36000, 1);
		this.addPotionEffect(PotionEffectType.LUCK, 36000, 1);
		this.addPotionEffect(PotionEffectType.FIRE_RESISTANCE, 36000, 0);
		this.addPotionEffect(PotionEffectType.NIGHT_VISION, 36000, 0);
		this.addLoreToItemMeta("A special mocktail created by frolf. Drink to enjoy some strawberry goodness and a whole variety of special buffs!");
		this.setColor(Color.fromRGB(252, 90, 141));
	}
	
	
}
