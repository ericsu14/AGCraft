package com.joojet.plugins.mobs.metadata;

import com.joojet.plugins.mobs.equipment.Equipment;

public class EquipmentTypeMetadata extends AbstractMetadata <Equipment>
{
	public final static String EQUIPMENT_TYPE_TAG = "equipment-type";
	
	/** Creates a blank instance of the Equipment Type Metadata. */
	public EquipmentTypeMetadata ()
	{
		super(EQUIPMENT_TYPE_TAG, null); 
	}

	
	/** Creates a new metadata designed to store an equipment's custom identifier
	 *  ID into their persistent metadata container
	 *  @param equipment - Equipment instance containing the custom identifier */
	public EquipmentTypeMetadata(Equipment equipment) 
	{
		super(EQUIPMENT_TYPE_TAG, equipment);
	}
}
