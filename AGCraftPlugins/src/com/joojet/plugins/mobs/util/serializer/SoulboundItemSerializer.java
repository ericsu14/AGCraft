package com.joojet.plugins.mobs.util.serializer;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.joojet.plugins.mobs.enums.EquipmentTypes;

public class SoulboundItemSerializer extends AbstractDataSerializer<HashMap <UUID, List <EquipmentTypes>>> 
{
	public static String soulboundFileName = "soulbounded-cache.ser";
	
	public SoulboundItemSerializer() 
	{
		super(soulboundFileName);
	}

}
