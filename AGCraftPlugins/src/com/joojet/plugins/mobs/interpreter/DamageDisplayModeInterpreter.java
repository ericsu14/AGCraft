package com.joojet.plugins.mobs.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.DamageDisplayMode;

public class DamageDisplayModeInterpreter extends AbstractInterpreter<DamageDisplayMode> 
{
	/** Key used to reference the damage display mode */
	public static String DAMAGE_DISPLAY_MODE_KEY = "damage-display-mode";
	
	public DamageDisplayModeInterpreter ()
	{
		super (DamageDisplayMode.values());
	}
}
