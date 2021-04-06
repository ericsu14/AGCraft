package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;

/** A poisonous splash potion thrown by Mr. Johnson */
public class SnakeVenomPotion extends SplashPotionEquipment 
{

	public SnakeVenomPotion() 
	{
		super(EquipmentType.SNAKE_VENOM, ChatColor.GREEN);
	}

	@Override
	protected void addPotionData() 
	{
		this.setColor(Color.LIME);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.setDisplayName("Snake Venom");
		this.addPotionEffect(PotionEffectType.POISON, 200, 0);
		this.addPotionEffect(PotionEffectType.BLINDNESS, 140, 0);
		this.addPotionEffect(PotionEffectType.CONFUSION, 140, 0);
		this.addLoreToItemMeta(StringUtil.alternateTextColors("Mr. Johnson", TextPattern.WORD, ChatColor.BLUE, ChatColor.GOLD) + "'s special delivery bundled with lots of love!" + ChatColor.LIGHT_PURPLE + " uwu");
	}

}
