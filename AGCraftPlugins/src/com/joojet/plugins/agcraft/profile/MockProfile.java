package com.joojet.plugins.agcraft.profile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

public class MockProfile implements PlayerProfile {
	
	protected UUID profileUUID;
	protected HashMap <String, ProfileProperty> profilePropetries;
	
	public MockProfile (UUID uuid)
	{
		this.profileUUID = uuid;
		this.profilePropetries = new HashMap <String, ProfileProperty> ();
	}
	
	
	@Override
	public void clearProperties() 
	{
		// TODO Auto-generated method stub
		this.profilePropetries.clear();
	}

	@Override
	public boolean complete(boolean arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean complete(boolean arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean completeFromCache() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean completeFromCache(boolean arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean completeFromCache(boolean arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProfileProperty> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasProperty(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProperty(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID setId(UUID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Collection<ProfileProperty> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperty(ProfileProperty arg0) {
		// TODO Auto-generated method stub

	}

}
