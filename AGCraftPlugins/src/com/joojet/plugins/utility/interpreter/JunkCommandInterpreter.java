package com.joojet.plugins.utility.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.utility.enums.JunkClassifier;

public class JunkCommandInterpreter extends AbstractInterpreter <JunkClassifier>
{
	public JunkCommandInterpreter ()
	{
		super (JunkClassifier.values());
	}
}
