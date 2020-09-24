package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class WeaponTypes extends EquipmentTypes 
{

	public WeaponTypes(EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.WEAPONS, equipmentTypeInterpreter);
		this.registerEquipments(
			new AngelOfDeath (ChatColor.LIGHT_PURPLE),
			new ATerribleFate (ChatColor.DARK_RED),
			new BarneyDagger (ChatColor.DARK_PURPLE),
			new BruinSword (ChatColor.AQUA),
			new BruinBow(),
			new DoomBlade (ChatColor.DARK_RED),
			new EnhancedStoneSword (ChatColor.GREEN),
			new EternalSpiritOfTroy (),
			new FightOn (ChatColor.GOLD),
			new FireVenomFang (ChatColor.LIGHT_PURPLE),
			new FireworkLauncher (ChatColor.GOLD),
			new LeftCrashSymbol (ChatColor.GOLD),
			new OgreAxe (ChatColor.DARK_GREEN),
			new PerpetualTorment (ChatColor.GOLD),
			new PharaohStaff (ChatColor.LIGHT_PURPLE),
			new PiglinAxe (ChatColor.GOLD),
			new PiglinSword (ChatColor.LIGHT_PURPLE),
			new PotentBow (ChatColor.GREEN),
			new SharpenedIronSword (ChatColor.LIGHT_PURPLE),
			new ShotBow (ChatColor.GOLD),
			new SpiderTooth (ChatColor.GREEN),
			new SpiritualFantasy (ChatColor.GOLD),
			new SpiritualTravesty (ChatColor.GOLD),
			new TerminatorSword (ChatColor.DARK_RED),
			new TheTrojanDestroyer (),
			new TrojanSword (ChatColor.RED),
			new VeryPotentBow (ChatColor.BLUE)
		);
	}
	
}
