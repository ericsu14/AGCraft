package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class BootTypes extends EquipmentTypes
{
	public BootTypes(EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.BOOTS, equipmentTypeInterpreter);
		this.registerEquipments(
			new BarneyFeet(ChatColor.DARK_PURPLE),
			new BruinFootballBoots (ChatColor.GOLD),
			new DesertSandals (ChatColor.GREEN),
			new DoomGuyFeet (ChatColor.DARK_RED),
			new LetItGo (ChatColor.GOLD),
			new LightweightChainmailBoots (ChatColor.GREEN),
			new LightweightDiamondBoots (ChatColor.GOLD),
			new LightweightIronBoots (ChatColor.LIGHT_PURPLE),
			new LightweightNetheriteBoots (ChatColor.GOLD),
			new OgreBoots (ChatColor.DARK_GREEN),
			new PatrioticBlueBoots (),
			new RoyalGoldBoots (ChatColor.LIGHT_PURPLE),
			new SkullKidBoots (ChatColor.DARK_RED),
			new USCSpikedBoots (ChatColor.GOLD)
		);
	}

}
