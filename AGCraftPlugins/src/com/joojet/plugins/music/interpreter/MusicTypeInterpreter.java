package com.joojet.plugins.music.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.music.enums.MusicType;

public class MusicTypeInterpreter extends AbstractInterpreter<MusicType> 
{
	public MusicTypeInterpreter ()
	{
		super (MusicType.values());
	}
}
