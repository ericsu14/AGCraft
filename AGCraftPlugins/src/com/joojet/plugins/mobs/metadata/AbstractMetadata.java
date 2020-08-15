package com.joojet.plugins.mobs.metadata;

import org.bukkit.NamespacedKey;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;


public abstract class AbstractMetadata <E> extends FixedMetadataValue 
{
	/** Tag identifier for the entity's metadata */
	public String tag = "";
	/** Stores the object associated with the metadata */
	protected E type;
	
	public AbstractMetadata (String tag, E type)
	{
		super (AGCraftPlugin.plugin, type);
		this.tag = tag;
		this.type = type;
	}
	
	public NamespacedKey generateNamespacedKey ()
	{
		return new NamespacedKey (AGCraftPlugin.plugin, this.tag);
	}
	
	/** Returns the object's tag used as an identifier for the metadata */
	public String getTag ()
	{
		return this.tag;
	}
	
	/** Returns the monster identifier as a string */
	@Override
	public String asString() 
	{
		return type.toString();
	}
	
	/** Returns the monster type identifier object */
	@Override
	public Object value() 
	{
		return this.type;
	}
	
	/** Returns an instance to the AGCraft plugin */
	@Override
	public Plugin getOwningPlugin() 
	{
		return AGCraftPlugin.plugin;
	}
	
	@Override
	public int asInt() 
	{
		return 0;
	}
	
	@Deprecated
	@Override
	public boolean asBoolean() 
	{
		return false;
	}
	
	@Deprecated
	@Override
	public byte asByte() 
	{
		return 0;
	}
	
	@Deprecated
	@Override
	public double asDouble() 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Deprecated
	@Override
	public float asFloat() 
	{
		return 0;
	}
	
	@Deprecated
	@Override
	public long asLong() 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Deprecated
	@Override
	public short asShort() 
	{
		return 0;
	}

	
	@Deprecated
	@Override
	public void invalidate() { }
}
