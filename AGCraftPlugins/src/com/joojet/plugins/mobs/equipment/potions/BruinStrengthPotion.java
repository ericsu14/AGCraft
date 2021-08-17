package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;


public class BruinStrengthPotion extends SplashPotionEquipment 
{
	public BruinStrengthPotion ()
	{
		super (EquipmentType.BRUIN_STRENGTH_POTION, ChatColor.AQUA);
	}
	
	@Override
	protected void addPotionData() 
	{
		this.setDisplayName(UCLAFaction.generateUCLADisplayName("Strength Potion"));
		this.addPotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 0);
		this.setColor(Color.MAROON);
	}

}
