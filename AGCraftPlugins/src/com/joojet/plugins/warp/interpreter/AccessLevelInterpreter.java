package com.joojet.plugins.warp.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.warp.constants.*;

public class AccessLevelInterpreter extends AbstractInterpreter <WarpAccessLevel>
{
	public AccessLevelInterpreter ()
	{
		super (WarpAccessLevel.values());
	}
}

