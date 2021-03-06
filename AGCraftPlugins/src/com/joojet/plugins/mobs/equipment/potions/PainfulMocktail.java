package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;

public class PainfulMocktail extends SplashPotionEquipment 
{
	public PainfulMocktail() 
	{
		super(EquipmentType.PAINFUL_MOCKTAIL, ChatColor.DARK_RED);
	}

	@Override
	protected void addPotionData() 
	{
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		
		this.setColor(Color.BLACK);
		
		this.setDisplayName(StringUtil.alternateTextColors("Painfully Bad Mocktail", TextPattern.WORD, ChatColor.DARK_RED, ChatColor.DARK_GRAY));
		this.addLoreToItemMeta("A mocktail so badly made that even standing near this questionable concoction will inflict great pain and make you ill.");
		this.addPotionEffect(PotionEffectType.HARM, 1, 0);
		this.addPotionEffect(PotionEffectType.HUNGER, 280, 0);
		this.addPotionEffect(PotionEffectType.CONFUSION, 280, 0);
	}

}
