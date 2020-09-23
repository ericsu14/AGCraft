package com.joojet.plugins.mobs.equipment.cake;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.EquipmentTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public class CakeTypes extends EquipmentTypes
{
	public CakeTypes (EquipmentTypeInterpreter interpreter)
	{
		super (EquipmentClassifier.CAKE, interpreter);
		this.registerEquipments(new SprinklesCake ());
	}
}
