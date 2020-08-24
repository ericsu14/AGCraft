package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

public class EternalRusheeMocktail extends PotionEquipment 
{
	
	public EternalRusheeMocktail(ChatColor color) 
	{
		super(color);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName("The Eternal Mocktail");
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 7;
		
		this.addPotionEffect(PotionEffectType.SPEED, 36000, 1);
		this.addPotionEffect(PotionEffectType.JUMP, 36000, 1);
		this.addPotionEffect(PotionEffectType.HEALTH_BOOST, 36000, 4);
		this.addPotionEffect(PotionEffectType.FAST_DIGGING, 36000, 1);
		this.addPotionEffect(PotionEffectType.LUCK, 36000, 1);
		this.addPotionEffect(PotionEffectType.NIGHT_VISION, 36000, 0);
		this.addLoreToItemMeta("This mocktail has a rather sweet "
				+ "taste and an almost mysterious charm about it that makes ya want to "
				+ "rush AΓΩ forever and ever hallelujah amen!");
		this.setColor(Color.fromRGB(253, 236, 213));
		
	}

}
