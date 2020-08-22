package com.joojet.plugins.mobs.metadata;

import java.util.UUID;

public class BossMetadata extends AbstractMetadata<UUID> 
{
	public static final String BOSS_TAG = "boss-tag";
	
	public BossMetadata ()
	{
		super(BOSS_TAG, null);
	}
	
	/** Creates a Boss Metadata with an ID */
	public BossMetadata(UUID id) 
	{
		super(BOSS_TAG, id);
	}
}
