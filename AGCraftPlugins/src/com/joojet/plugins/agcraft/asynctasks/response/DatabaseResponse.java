package com.joojet.plugins.agcraft.asynctasks.response;

public class DatabaseResponse <E>
{
	protected E data;
	
	protected String message;
	
	protected boolean status;
	
	public DatabaseResponse (E data, boolean status)
	{
		this.data = data;
		this.message = "";
		this.status = status;
	}
	
	public DatabaseResponse (E data, String message, boolean status)
	{
		this.data = data;
		this.message = message;
		this.status = status;
	}
	
	public E getData ()
	{
		return this.data;
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
