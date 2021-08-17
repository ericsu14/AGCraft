package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class TrojanSwiftnessPotion extends SplashPotionEquipment
{

	public TrojanSwiftnessPotion() 
	{
		super(EquipmentType.TROJAN_SWIFTNESS_POTION, ChatColor.RED);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(USCFaction.generateUSCDisplayName("Swiftness Potion"));
		this.addPotionEffect(PotionEffectType.SPEED, 1800, 0);
		this.setColor(Color.AQUA);
	}

}
