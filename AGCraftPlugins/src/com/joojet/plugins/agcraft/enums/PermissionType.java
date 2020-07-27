package com.joojet.plugins.agcraft.enums;

public enum PermissionType 
{
	PLAYER ("agcraft.player"), ADMIN ("agcraft.admin"), ALL ("agcraft.player");
	
	private String permission;
	
	/** Constructs a new CommandType specifying the permission level of this command */
	private PermissionType (String permission)
	{
		this.permission = permission;
	}
	
	/** Returns the command permission associated with this command */
	public String getPermission ()
	{
		return this.permission;
	}
	
	/** Returns the command's name */
	@Override
	public String toString ()
	{
		return this.name();
	}
}
