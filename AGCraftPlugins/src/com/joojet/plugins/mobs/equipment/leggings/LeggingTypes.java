package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class LeggingTypes extends EquipmentTypes 
{

	public LeggingTypes(EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.LEGGINGS, equipmentTypeInterpreter);
		this.registerEquipments(
			new BarneyLegs (ChatColor.LIGHT_PURPLE),
			new BruinLeggings (ChatColor.AQUA),
			new DarkNetheriteLeggings (ChatColor.DARK_RED),
			new DoomGuyLegs (ChatColor.DARK_RED),
			new OgreLeggings (ChatColor.DARK_GREEN),
			new PatrioticWhiteLeggings (),
			new PigminTrousers (ChatColor.LIGHT_PURPLE),
			new ReinforcedChainmailLeggings (ChatColor.GREEN),
			new ReinforcedDiamondLeggings (ChatColor.GOLD),
			new ReinforcedIronLeggings (ChatColor.LIGHT_PURPLE),
			new RoyalGoldLeggings (ChatColor.LIGHT_PURPLE),
			new SkullKidPants (ChatColor.DARK_RED),
			new USCBandUniformBottom (ChatColor.GOLD),
			new USCFootballTrousers (ChatColor.GOLD),
			new MrJohnsonLeggings (ChatColor.DARK_PURPLE)
		);
	}
	
}
