package com.joojet.plugins.mobs.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;

public class SummoningScrollInterpreter extends AbstractInterpreter<SummoningScroll>
{
	public SummoningScrollInterpreter ()
	{
		super ();
		
		// Loads in Monster names for legacy summoning scroll support
		for (SummonTypes type : SummonTypes.values())
		{
			this.insertWord(type.getMonsterName(), type.getSummon());
			this.insertWord(type.getSummon().toString(), type.getSummon());
		}
	}
}
