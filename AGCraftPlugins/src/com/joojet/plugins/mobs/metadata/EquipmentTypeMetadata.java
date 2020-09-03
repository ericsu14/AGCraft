package com.joojet.plugins.mobs.metadata;

import com.joojet.plugins.mobs.enums.EquipmentTypes;

public class EquipmentTypeMetadata extends AbstractMetadata <EquipmentTypes>
{
	public static String equipmentTag = "equipment-tag";
	
	public EquipmentTypeMetadata ()
	{
		super (equipmentTag, EquipmentTypes.NONE);
	}
	
	public EquipmentTypeMetadata (EquipmentTypes type)
	{
		super (equipmentTag, type);
	}
}
