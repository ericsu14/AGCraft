package com.joojet.plugins.utility.interpreter;

import org.bukkit.Material;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;

public class MaterialInterpreter extends AbstractInterpreter<Material> 
{
	public MaterialInterpreter ()
	{
		super (Material.values());
	}
}
