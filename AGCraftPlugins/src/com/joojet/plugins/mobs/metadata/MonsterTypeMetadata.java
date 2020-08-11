package com.joojet.plugins.mobs.metadata;
import com.joojet.plugins.mobs.enums.MonsterType;

public class MonsterTypeMetadata extends AbstractMetadata <MonsterType> 
{	
	/** A simple implementation of Spigot's MetadataValue allowing
	 *  custom mob names to be stored as metadata within an entity. */
	public MonsterTypeMetadata (MonsterType type)
	{
		super ("monster-type", type);
	}
}
