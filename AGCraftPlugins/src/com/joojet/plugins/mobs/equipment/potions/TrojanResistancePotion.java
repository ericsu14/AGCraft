package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class TrojanResistancePotion extends SplashPotionEquipment
{

	public TrojanResistancePotion() 
	{
		super(EquipmentType.TROJAN_RESISTANCE_POTION, ChatColor.RED);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(USCFaction.generateUSCDisplayName("Resistance Potion"));
		this.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1800, 0);
		this.setColor(Color.GRAY);
	}

}
