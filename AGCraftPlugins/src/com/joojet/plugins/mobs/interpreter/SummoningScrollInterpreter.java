package com.joojet.plugins.mobs.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.SummonTypes;

public class SummoningScrollInterpreter extends AbstractInterpreter<SummonTypes>
{
	public SummoningScrollInterpreter ()
	{
		super (SummonTypes.values());
		
		// Loads in Monster names for legacy summoning scroll support
		for (SummonTypes type : SummonTypes.values())
		{
			this.insertWord(type.getMonsterName(), type);
		}
	}
}
