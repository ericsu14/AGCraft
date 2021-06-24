package com.joojet.plugins.agcraft.interfaces;

public interface AGListener
{	
	/** Allows the listener to implement their own setup routines upon enabling */
	public void onEnable ();
	
	/** Allows the listener to implement their own cleanup code upon disabling */
	public void onDisable ();
}
