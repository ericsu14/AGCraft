package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;

public class MrJohnsonSpecialPotion extends SplashPotionEquipment {

	public MrJohnsonSpecialPotion() 
	{
		super(EquipmentType.MR_JOHNSON_SPECIAL, ChatColor.DARK_BLUE);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(StringUtil.alternateTextColors("Mr. Johnson's Special Suprise", TextPattern.WORD, 
				ChatColor.BLUE, ChatColor.GOLD));
		this.loreColor = ChatColor.GOLD;
		this.setColor(Color.PURPLE);
		this.addLoreToItemMeta("Mr. Johnson wants you to experience perfection. 25 cents is all it takes.");
		this.addPotionEffect(PotionEffectType.WITHER, 140, 0);
		this.addPotionEffect(PotionEffectType.WEAKNESS, 140, 0);
		this.addPotionEffect(PotionEffectType.HARM, 1, 1);
	}

}
