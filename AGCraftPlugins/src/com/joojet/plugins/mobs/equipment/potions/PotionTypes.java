package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class PotionTypes extends EquipmentTypes
{
	public PotionTypes (EquipmentTypeInterpreter equipmentTypeInterpreter) 
	{
		super(EquipmentClassifier.POTIONS, equipmentTypeInterpreter);
		this.registerEquipments(
			new EnhancedHastePotion (ChatColor.GOLD),
			new EnhancedLuckPotion (ChatColor.GOLD),
			new EnhancedSpeedPotion (ChatColor.GOLD),
			new EnhancedStrengthPotion (ChatColor.GOLD),
			new HastePotion (ChatColor.GOLD),
			new LuckPotion (ChatColor.GOLD),
			new EternalRusheeMocktail (ChatColor.YELLOW),
			new StrawberryMocktail (ChatColor.LIGHT_PURPLE)
		);
	}
}
