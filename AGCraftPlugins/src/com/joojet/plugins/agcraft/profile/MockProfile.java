package com.joojet.plugins.agcraft.profile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

/** Represents a mock profile used to store custom skin data for playerheads */
public class MockProfile implements PlayerProfile 
{
	
	protected String profileName;
	protected UUID profileUUID;
	protected Set <ProfileProperty> profilePropetries;
	
	public MockProfile ()
	{
		this.profileUUID = UUID.randomUUID();
		this.profileName = "";
		this.profilePropetries = new HashSet <ProfileProperty> ();
	}
	
	
	@Override
	public void clearProperties() 
	{
		this.profilePropetries.clear();
	}

	@Override
	public boolean complete(boolean arg0)
	{
		return true;
	}

	@Override
	public boolean complete(boolean arg0, boolean arg1) 
	{
		return true;
	}

	@Override
	public boolean completeFromCache() 
	{
		return true;
	}

	@Override
	public boolean completeFromCache(boolean arg0) 
	{
		return true;
	}

	@Override
	public boolean completeFromCache(boolean arg0, boolean arg1) 
	{
		return true;
	}

	@Override
	public UUID getId() 
	{
		return this.profileUUID;
	}

	@Override
	public String getName() 
	{
		return this.profileName;
	}

	@Override
	public Set<ProfileProperty> getProperties() {
		// TODO Auto-generated method stub
		return this.profilePropetries;
	}
	
	/** Returns true if this mock profile instance has the property referenced
	 *  by the passed name.
	 *  @param name - Name of the property. */
	@Override
	public boolean hasProperty(String name) 
	{
		for (ProfileProperty propetry : this.profilePropetries)
		{
			if (propetry.getName().equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isComplete() 
	{
		return true;
	}

	/** Removes a property referenced by its name from this mock profile instance.
	 *  Returns true if the removal is sucessful. False otherwise
	 *  @param name - Name of the propetry */
	@Override
	public boolean removeProperty(String name) 
	{
		ProfileProperty remove = null;
		for (ProfileProperty propetry : this.profilePropetries)
		{
			if (propetry.getName().equalsIgnoreCase(name))
			{
				remove = propetry;
				break;
			}
		}
		
		if (remove != null)
		{
			this.profilePropetries.remove(remove);
			return true;
		}
		return false;
	}

	/** Sets the mock profile's UUID
	 * 	@param uuid - The new UUID
	 * 	@return The old UUID */
	@Override
	public UUID setId(UUID uuid) 
	{
		UUID oldUUID = this.profileUUID;
		this.profileUUID = uuid;
		return oldUUID;
	}
	
	/** Sets the mock profile's name
	 * 	@param name - The new name
	 * 	@return The old name */
	@Override
	public String setName(String name) 
	{
		String oldName = this.profileName;
		this.profileName = name;
		return oldName;
	}
	
	/** Sets multiple properties for this mock profile.
	 * 	@param properties - A collection of properties to be added */
	@Override
	public void setProperties(Collection<ProfileProperty> properties) 
	{
		this.profilePropetries.addAll(properties);
	}
	
	/** Sets a property. If the property already exists, the previous one will be replaced.
	 * 	@param property - The property to be set*/
	@Override
	public void setProperty(ProfileProperty property) 
	{
		this.profilePropetries.add(property);
	}

}
