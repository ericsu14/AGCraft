package com.joojet.plugins.agcraft.util;

public class Pair <K, E>
{
	/** The pair's key */
	public K key;
	/** The pair's entry */
	public E entry;
	
	/** Constructs a new pair
	 *  @param key Pair's key
	 *  @param entry Pair's entry */
	public Pair (K key, E entry)
	{
		this.key = key;
		this.entry = entry;
	}
	
	/** Gets the pair's key */
	public K getKey ()
	{
		return this.key;
	}
	
	/** Gets the pair's entry */
	public E getEntry ()
	{
		return this.entry;
	}
	
	/** Sets the pair's key */
	public void setKey (K key)
	{
		this.key = key;
	}
	
	/** Sets the pair's entry */
	public void setEntry (E entry)
	{
		this.entry = entry;
	}
}
