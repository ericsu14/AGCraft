package com.joojet.plugins.mobs.metadata;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;


public abstract class AbstractMetadata <E>
{
	/** Tag identifier for the entity's metadata */
	public String tag = "";
	/** Stores the object associated with the metadata */
	protected E value;
	
	public AbstractMetadata (String tag, E value)
	{
		this.tag = tag;
		this.value = value;
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
	public String asString() 
	{
		return value.toString();
	}
	
	/** Returns the monster type identifier object */
	public Object value() 
	{
		return this.value;
	}
	
	/** Adds the value's toString data defined in this class into a PersistentDataHolder's data container */
	public void addStringMetadata (PersistentDataHolder holder)
	{
		holder.getPersistentDataContainer().set(this.generateNamespacedKey(), PersistentDataType.STRING, this.asString());
	}
	
	/** Returns the value's metadata stored by this class's instance as a string */
	public String getStringMetadata (PersistentDataHolder holder)
	{
		NamespacedKey key = this.generateNamespacedKey();
		if (holder.getPersistentDataContainer().has(key, PersistentDataType.STRING))
		{
			return holder.getPersistentDataContainer().get(key, PersistentDataType.STRING);
		}
		return null;
	}
	
}
