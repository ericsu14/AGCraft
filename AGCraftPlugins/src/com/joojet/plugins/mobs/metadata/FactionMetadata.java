package com.joojet.plugins.mobs.metadata;

import com.joojet.plugins.mobs.enums.Faction;

public class FactionMetadata extends AbstractMetadata<Faction> 
{
	/** Stores the tag used to reference this specific metadata */
	public static final String FACTION_TAG = "faction_type";
	
	/** Creates a new instance of a faction.
	 * 	@param type - Type of faction this metadata represents */
	public FactionMetadata(Faction type) 
	{
		super(FACTION_TAG, type);
	}
	
}
