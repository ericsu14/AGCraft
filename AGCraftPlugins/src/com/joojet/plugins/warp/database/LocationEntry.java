package com.joojet.plugins.warp.database;

import org.bukkit.World.Environment;

import com.joojet.plugins.warp.constants.WarpAccessLevel;

public class LocationEntry 
{
	/** UUID of the player */
	private String uuid;
	/** Name of the location */
	private String locationName;
	/** x, y, and z coords */
	private double x, y, z;
	/** Access level */
	private WarpAccessLevel level;
	/** Environment */
	private Environment env;
	
	/** Constructs a new LocationEntry */
	public LocationEntry (String uuid, String name, double x, double y, double z, String level, String env)
	{
		this.uuid = uuid;
		this.locationName = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.level = this.convertStringToAccessLevel(level);
		this.env = this.convertStringToEnvironment(env);
	}
	
	/** Converts an access level to a WarpAccessLevel enum */
	public WarpAccessLevel convertStringToAccessLevel (String level)
	{
		if (level.equalsIgnoreCase(WarpAccessLevel.PRIVATE.name()))
		{
			return WarpAccessLevel.PRIVATE;
		}
		return WarpAccessLevel.PUBLIC;
	}
	
	/** Converts an environment string to a Environment enum */
	public Environment convertStringToEnvironment (String env)
	{
		if (env.equalsIgnoreCase(Environment.NETHER.name()))
		{
			return Environment.NETHER;
		}
		else if (env.equalsIgnoreCase(Environment.THE_END.name()))
		{
			return Environment.THE_END;
		}
		else
		{
			return Environment.NORMAL;
		}
	}

	public String getUuid() 
	{
		return uuid;
	}

	public void setUuid(String uuid) 
	{
		this.uuid = uuid;
	}

	public String getLocationName() 
	{
		return locationName;
	}

	public void setLocationName(String locationName) 
	{
		this.locationName = locationName;
	}

	public double getX() 
	{
		return x;
	}

	public void setX(double x) 
	{
		this.x = x;
	}

	public double getY() 
	{
		return y;
	}

	public void setY(double y) 
	{
		this.y = y;
	}

	public double getZ() 
	{
		return z;
	}

	public void setZ(double z) 
	{
		this.z = z;
	}

	public WarpAccessLevel getAccessLevel() 
	{
		return level;
	}

	public void setAccesssLevel(WarpAccessLevel level) 
	{
		this.level = level;
	}

	public Environment getEnvironment() 
	{
		return env;
	}

	public void setEnvironment(Environment env) 
	{
		this.env = env;
	}
}
