package com.joojet.biblefetcher.trie;

import java.util.Hashtable;
import java.util.Map.Entry;

public class TrieNode <T> 
{
	// Defines the max. amount of children this node could store
	public final int kChildSize = 255;
	private Hashtable <T, Integer> linkedIDs;
	// Stores the node's children
	private TrieNode<T> children[];
	// Amount of active children this node carries
	private int size;
	// The node's current character
	private char letter;
	
	@SuppressWarnings("unchecked")
	public TrieNode (char letter, T linkedID)
	{
		this.letter = letter;
		this.size = 0;
		this.children = (TrieNode<T>[]) new TrieNode [this.kChildSize];
		this.linkedIDs = new Hashtable <T, Integer> ();
		this.updateFrequency(linkedID);
	}
	
	public void addChild (char letter, T linkedID)
	{
		this.children[letter] = new TrieNode <T> (letter, linkedID);
		++this.size;
	}
	
	public TrieNode <T> next (char letter)
	{
		return this.children[letter];
	}
	
	public int size ()
	{
		return this.size;
	}
	
	/** Returns the linked id with the highest frequency */
	public T getLinkedID (String searchTerm)
	{
		T result = null;
		int currentCount = -1;
		for (Entry<T, Integer> entry : this.linkedIDs.entrySet())
		{
			/* Check if length of searchTerm matches length of formatted title */
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
	
	public char getLetter ()
	{
		return this.letter;
	}
	
	/** Each node in this trie could be tied to multiple bible IDs. These bible IDs are tracked
	 *  with an internal hashtable that keeps track of the frequency of these bible IDs.
	 *  This function updates that count. */
	public void updateFrequency (T id)
	{
		// Null check
		if (id == null)
		{
			return;
		}

		if (!this.linkedIDs.containsKey (id))
		{
			this.linkedIDs.put(id, 1);
		}
		else
		{
			int updCount = this.linkedIDs.get(id) + 1;
			this.linkedIDs.replace(id, updCount);
		}
	}
	
	
}
