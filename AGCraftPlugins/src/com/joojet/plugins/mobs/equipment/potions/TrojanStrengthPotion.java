package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class TrojanStrengthPotion extends SplashPotionEquipment {

	public TrojanStrengthPotion() 
	{
		super(EquipmentType.TROJAN_STRENGTH_POTION, ChatColor.RED);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(USCFaction.generateUSCDisplayName("Strength Potion"));
		this.addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 0);
		this.setColor(Color.MAROON);
	}

}
