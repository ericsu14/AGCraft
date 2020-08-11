package com.joojet.plugins.agcraft.interfaces;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;

public abstract class AbstractInterpreter <E>
{
	/** Root node for the internal search trie */
	protected TrieNode <E> root;
	/** Array of values used to populate the trie */
	protected E[] values;
	
	/** Creates a new instance of the search term interpreter
	 *  with an initially empty search trie. */
	public AbstractInterpreter ()
	{
		this.root = new TrieNode <E> (' ', null);
		this.values = null;
	}
	/** Creates a new instance of the search term interpreter.
	 *  This class stores the list of values into an internal search trie,
	 *  where the value's toString() method is used as a key.
	 *  @param values - List of values to be inserted into this trie */
	public AbstractInterpreter (E[] values)
	{
		this.root = new TrieNode <E> (' ', null);
		this.values = values;
		this.populateTrie();
	}
	
	/** Populates the search trie with the set of values that are passed into this
	 *  interpreter instance, where the value's toString() method is used as the key
	 *  and the value itself is used as the element. */
	protected void populateTrie ()
	{
		for (E element : this.values)
		{
			TrieUtil.insertWord(element.toString(), element, this.root);
		}
	}
	
	/** Inserts a new entry into the trie
	 * 		@param key - Entry's key that is used for lookup
	 * 		@param value - Entry's value that is referenced by the key */
	public void insertWord (String key, E value)
	{
		TrieUtil.insertWord(key, value, this.root);
	}
	
	/** Searches the internal search trie for a stored value that is referenced by its
	 *  own toString() method as a key.
	 *   @param input - Search term
	 *   @return - The value referenced by the search term if it is stored in the search trie. Returns NULL otherwise.*/
	public E searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.root);
	}
	
}
