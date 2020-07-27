package com.joojet.plugins.agcraft.enums;

public enum CommandType 
{
	BIBLE (PermissionType.ALL),
	CLEAR_BIBLES (PermissionType.ALL),
	GET_COORDINATES (PermissionType.PLAYER),
	CLEAR_JUNK (PermissionType.PLAYER),
	AUTOSMELT (PermissionType.PLAYER),
	WARP (PermissionType.PLAYER),
	SET_LOCATION (PermissionType.PLAYER),
	REMOVE_LOCATION (PermissionType.PLAYER),
	GIVE_RESPAWN_TICKET (PermissionType.ADMIN),
	REMOVE_OLD_NETHER_LOCATIONS (PermissionType.ADMIN),
	GRANT_REWARD (PermissionType.ADMIN),
	REWARDS (PermissionType.PLAYER),
	TOGGLE_DEBUG_MODE (PermissionType.ADMIN),
	PUNISH_PLAYER (PermissionType.ADMIN),
	FORGIVE_PLAYER (PermissionType.ADMIN);
	
	private PermissionType permissionType;
	
	/** Constructs a new command type, used to represent all commands in this server
	 * 		@param PermissionType - Command's permission level */
	private CommandType (PermissionType permissionType)
	{
		this.permissionType = permissionType;
	}
	
	
	/** Returns the command's permission type */
	public PermissionType getPermissionType ()
	{
		return this.permissionType;
	}
	
	/** Returns a human friendly version of the command name */
	@Override
	public String toString ()
	{
		StringBuilder str = new StringBuilder ();
		String[] tokens = this.name().toLowerCase().split("_");
		for (String t : tokens)
		{
			str.append(t);
		}
		return str.toString();
	}
}
