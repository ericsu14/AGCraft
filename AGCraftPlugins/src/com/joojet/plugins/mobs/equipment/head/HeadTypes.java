package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class HeadTypes extends EquipmentTypes 
{

	public HeadTypes(EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.HEAD, equipmentTypeInterpreter);
		this.registerEquipments(
			new AGSpotted (ChatColor.GOLD),
			new BarneyHead (ChatColor.DARK_PURPLE),
			new BruinBanditHead (),
			new BruinHead (),
			new CyborgPigmanHead (ChatColor.DARK_RED),
			new DarkNetheriteHelmet (ChatColor.GOLD),
			new DoomSlayerHead (ChatColor.DARK_RED),
			new FrostyFace (ChatColor.AQUA),
			new PharaohHead (ChatColor.LIGHT_PURPLE),
			new PiglinCaptainHead (ChatColor.GOLD),
			new PiglinHunterHelmet (ChatColor.BLUE),
			new PiglinSoldierHat (ChatColor.LIGHT_PURPLE),
			new ReinforcedChainmailHelmet (ChatColor.GREEN),
			new ReinforcedDiamondHelmet (ChatColor.GOLD),
			new ReinforcedIronHelmet (ChatColor.LIGHT_PURPLE),
			new ScruffyFace (ChatColor.GOLD),
			new ShrekHat (ChatColor.DARK_GREEN),
			new SkullKidHelmet (ChatColor.DARK_RED),
			new USAHat (),
			new USCBandHead (ChatColor.GOLD),
			new USCTrojan (ChatColor.GOLD),
			new ZombiePigmenHead (ChatColor.LIGHT_PURPLE),
			new RoyalPiglinHunterHead (ChatColor.RED)
		);
	}

}
