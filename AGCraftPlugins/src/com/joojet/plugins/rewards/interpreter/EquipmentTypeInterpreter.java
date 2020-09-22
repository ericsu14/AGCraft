package com.joojet.plugins.rewards.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.EquipmentTypes;

public class EquipmentTypeInterpreter extends AbstractInterpreter <EquipmentTypes>
{
	public EquipmentTypeInterpreter ()
	{
		super (EquipmentTypes.values());
	}
}
