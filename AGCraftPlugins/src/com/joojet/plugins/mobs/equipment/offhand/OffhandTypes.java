package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;
import com.joojet.plugins.mobs.enums.EquipmentClassifier;

public class OffhandTypes extends EquipmentTypes 
{
	public OffhandTypes(EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.OFFHAND, equipmentTypeInterpreter);
		
		this.registerEquipments(
			new BarneyTotem (ChatColor.DARK_PURPLE),
			new BlueAndGold (),
			new BruinShield (),
			new CaptainAmericaShield (ChatColor.GOLD),
			new CursedArrow (ChatColor.DARK_RED),
			new EnhancedWitheringArrow (ChatColor.GOLD),
			new HurtfulArrow (ChatColor.BLUE),
			new PigmanDagger (ChatColor.GOLD),
			new PoisonousArrow (ChatColor.DARK_GREEN),
			new PropFirework (),
			new RightCrashSymbol(ChatColor.GOLD),
			new TerminatorDagger (ChatColor.DARK_RED),
			new ThanosArrow (ChatColor.GOLD),
			new TotemOfEternalFaith (),
			new TotemOfEternalStrength (),
			new TotemOfTheEternalRushee (),
			new USCCreeperShield (),
			new WeakeningArrow (ChatColor.GREEN),
			new WitheringArrow (ChatColor.LIGHT_PURPLE)
		);
	}

}
