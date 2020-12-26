package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;

public class ChristmasMocktail extends PotionEquipment
{

	public ChristmasMocktail() 
	{
		super(EquipmentType.CHRISTMAS_MOCKTAIL, ChatColor.GREEN);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(StringUtil.alternateTextColors("Christmas Mocktail", TextPattern.WORD, ChatColor.RED, ChatColor.GREEN));
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 7;
		
		this.addPotionEffect(PotionEffectType.SPEED, 36000, 1);
		this.addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 36000, 1);
		this.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 36000, 2);
		this.addPotionEffect(PotionEffectType.FIRE_RESISTANCE, 36000, 0);
		this.addPotionEffect(PotionEffectType.HEALTH_BOOST, 36000, 4);
		this.addPotionEffect(PotionEffectType.FAST_DIGGING, 36000, 1);
		this.addPotionEffect(PotionEffectType.LUCK, 36000, 1);
		this.addPotionEffect(PotionEffectType.NIGHT_VISION, 36000, 0);
		this.addPotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 36000, 0);
		this.addPotionEffect(PotionEffectType.DOLPHINS_GRACE, 36000, 0);
		this.addPotionEffect(PotionEffectType.REGENERATION, 36000, 0);
		this.addLoreToItemMeta("Unleash your festive spirit with this Christmasy drink!");
		this.setColor(Color.fromRGB(253, 236, 213));
		this.makeSoulbound();
		
	}

}
