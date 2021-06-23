package com.joojet.biblefetcher.trie;

import java.util.HashMap;
import java.util.Map.Entry;

public class TrieNode <T> 
{
	/** Stores a list of values tied to this node */
	private HashMap <T, Integer> linkedValues;
	/** Stores the node's children, where the character is the key */
	private HashMap <Character, TrieNode<T>> children;
	/** Keeps track of the total amount of stored children carried by this node */
	private int size;
	/** The node's key */
	private char key;
	
	public TrieNode (char key, T value)
	{
		this.key = key;
		this.size = 0;
		this.children = new HashMap <Character, TrieNode <T>> ();
		this.linkedValues = new HashMap <T, Integer> ();
		this.linkSearchTerm(value);
	}
	
	/** Adds a new child node into this TrieNode. If there already exists
	 *  a child node under the passed key, then the value will instead be added
	 *  into the existing child's list of linked values.
	 *  @param key Key of the new child node.
	 *  @param value Value of the new node */
	public void addChild (char key, T value)
	{
		if (!this.children.containsKey(key))
		{
			this.children.put(key, new TrieNode <T> (key, value));
			++this.size;
		}
		else
		{
			this.children.get(key).linkSearchTerm(value);
		}
	}
	
	/** Returns a child node whose key matches the passed input, if it exists.
	 *  @param key The key of the child node being looked-up */
	public TrieNode <T> next (char key)
	{
		return this.children.get(key);
	}
	
	/** Returns the total number of children this node contains */
	public int size ()
	{
		return this.size;
	}
	
	/** Returns the node's value. If there are multiple values associated with this TrieNode
	 *  and the searchTerm does not match with any of the node's linked values,
	 *  the value that is most frequently inserted into this Trie will be returned.
	 *  @param searchTerm The string that is to be searched. */
	public T getValue (String searchTerm)
	{
		T result = null;
		int currentCount = Integer.MIN_VALUE;
		
		for (Entry<T, Integer> entry : this.linkedValues.entrySet())
		{	
			/* Check if length of searchTerm matches length of formatted title */
			if (this.linkedValues.entrySet().size() == 1 || entry.getKey().toString().equalsIgnoreCase(searchTerm))
			{
				return entry.getKey();
			}
			
			if (entry.getKey() instanceof Enum)
			{
				@SuppressWarnings("rawtypes")
				Enum id = (Enum) entry.getKey();
				// Returns the current ID if term matches either its name or toString property
				if (id.toString().equalsIgnoreCase(searchTerm) ||
						id.name().equalsIgnoreCase(searchTerm))
				{
					return entry.getKey();
				}
			}
			
			if (entry.getValue() > currentCount)
			{
				currentCount = entry.getValue();
				result = entry.getKey();
			}
		}
		return result;
	}
	
	/** Returns the TrieNode's character key */
	public char getKey ()
	{
		return this.key;
	}
	
	/** Adds a new search term into the TrieNode's internal list of linked values.
	 *  If this search term already exists in the TrieNode's linked value list,
	 *  then its frequency will instead be updated.
	 *  @param searchTerm Search term that is to be linked into this node */
	protected void linkSearchTerm (T searchTerm)
	{
		// Null check
		if (searchTerm == null)
		{
			return;
		}

		if (!this.linkedValues.containsKey (searchTerm))
		{
			this.linkedValues.put(searchTerm, 1);
		}
		else
		{
			int updCount = this.linkedValues.get(searchTerm) + 1;
			this.linkedValues.replace(searchTerm, updCount);
		}
	}
	
	
}
