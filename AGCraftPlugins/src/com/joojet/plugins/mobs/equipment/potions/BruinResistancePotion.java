package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;


public class BruinResistancePotion extends SplashPotionEquipment
{
	public BruinResistancePotion ()
	{
		super (EquipmentType.BRUIN_RESISTANCE_POTION, ChatColor.AQUA);
	}

	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(UCLAFaction.generateUCLADisplayName("Resistance Potion"));
		this.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2400, 0);
		this.setColor(Color.AQUA);
	}
	
	
}
