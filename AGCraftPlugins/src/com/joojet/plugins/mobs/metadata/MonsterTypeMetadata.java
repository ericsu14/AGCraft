package com.joojet.plugins.mobs.metadata;

import com.joojet.plugins.mobs.enums.MonsterType;

public class MonsterTypeMetadata extends AbstractMetadata <MonsterType> 
{	
	/** Identifier for the monster type metadata */
	public static final String MOB_TAG = "monster-tag";
	
	/** Creates a new instance of a MonsterTypeMetadata with a default value. */
	public MonsterTypeMetadata ()
	{
		super (MOB_TAG, MonsterType.NONE);
	}
	
	/** A simple implementation of Spigot's MetadataValue allowing
	 *  custom mob names to be stored as metadata within an entity. */
	public MonsterTypeMetadata (MonsterType type)
	{
		super (MOB_TAG, type);
	}
}
