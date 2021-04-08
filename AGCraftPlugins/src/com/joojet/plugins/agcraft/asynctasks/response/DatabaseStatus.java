package com.joojet.plugins.agcraft.asynctasks.response;

public class DatabaseStatus 
{
	protected String message;
	
	protected boolean status;
	
	public DatabaseStatus (String message, boolean status)
	{
		this.message = message;
		this.status = status;
	}
	
	public String getMessage ()
	{
		return this.message;
	}
	
	public boolean getStatus ()
	{
		return this.status;
	}
}
