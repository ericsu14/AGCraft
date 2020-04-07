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

	public String getUUID () 
	{
		return uuid;
	}

	public void setUUID (String uuid) 
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((env == null) ? 0 : env.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((locationName == null) ? 0 : locationName.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationEntry other = (LocationEntry) obj;
		if (env != other.env)
			return false;
		if (level != other.level)
			return false;
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
}
