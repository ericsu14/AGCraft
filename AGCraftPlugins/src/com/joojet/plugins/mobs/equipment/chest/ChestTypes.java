package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class ChestTypes extends EquipmentTypes 
{
	public ChestTypes(EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.CHEST, equipmentTypeInterpreter);
		this.registerEquipments(
				new BarneyChest (ChatColor.DARK_PURPLE),
				new BruinTunic (ChatColor.AQUA),
				new BulletproofChainmailChestplate (ChatColor.GREEN),
				new BulletproofIronChestplate (ChatColor.LIGHT_PURPLE),
				new CookieHeart (ChatColor.GOLD),
				new DarkNetheriteChestplate (ChatColor.GOLD),
				new DoomChest (ChatColor.DARK_RED),
				new OgreTunic (ChatColor.DARK_GREEN),
				new PatrioticRedJacket (),
				new ReinforcedChainmailChestplate (ChatColor.GREEN),
				new ReinforcedDiamondChestplate (ChatColor.GOLD),
				new RoyalGoldChestplate (ChatColor.LIGHT_PURPLE),
				new SkullKidChest (ChatColor.DARK_RED),
				new SnowballHeart (ChatColor.AQUA),
				new ThePecks (ChatColor.GOLD),
				new USCBandUniformTop (ChatColor.GOLD),
				new USCFootballTunic (ChatColor.GOLD)
		);
	}

}
