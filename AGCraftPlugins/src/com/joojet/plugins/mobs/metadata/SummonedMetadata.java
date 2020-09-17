package com.joojet.plugins.mobs.metadata;

public class SummonedMetadata extends AbstractMetadata <String>
{
	public static final String tag = "summoned-metadata";
	
	public SummonedMetadata ()
	{
		super (tag, "");
	}
	
	public SummonedMetadata(String value) 
	{
		super(tag, value);
	}

}
