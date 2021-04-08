package com.joojet.plugins.agcraft.asynctasks.response;

public class DatabaseResponse <E> extends DatabaseStatus
{	
	protected E data;
	
	public DatabaseResponse (String message, boolean status)
	{
		super (message, status);
		this.data = null;
	}
	
	public DatabaseResponse (E data, boolean status)
	{
		super ("", status);
		this.data = data;
	}
	
	public DatabaseResponse (E data, String message, boolean status)
	{
		super (message, status);
		this.data = data;
	}
	
	public E getData ()
	{
		return this.data;
	}

}
