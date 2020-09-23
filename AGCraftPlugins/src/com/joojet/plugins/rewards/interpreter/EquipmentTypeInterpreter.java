package com.joojet.plugins.rewards.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.EquipmentType;

public class EquipmentTypeInterpreter extends AbstractInterpreter <EquipmentType>
{
	public EquipmentTypeInterpreter ()
	{
		super (EquipmentType.values());
	}
}
