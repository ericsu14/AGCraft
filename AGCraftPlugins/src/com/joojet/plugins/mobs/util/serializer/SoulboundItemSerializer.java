package com.joojet.plugins.mobs.util.serializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SoulboundItemSerializer extends AbstractDataSerializer<HashMap <UUID, List <Map <String, Object>>>> 
{
	public static String soulboundFileName = "soulbounded-cache.ser";
	
	public SoulboundItemSerializer() 
	{
		super(soulboundFileName);
	}

}
