/** A set of utility functions used for either inserting or adding content into 
 *  our search tries. */
package com.joojet.biblefetcher.trie;

public class TrieUtil 
{
	/** Searches the trie using the input String and returns the linkedID 
	  * associated with that search term.
	  * @param input - Search term
	  * @param root - Pointer to root node of the trie being searched
	  * @return - The linkedID associated with the search term, or null if not found.*/
	public static <T> T searchTrie (String input, TrieNode <T> root)
	{
		input = input.toLowerCase();
		TrieNode <T> curr = root;
		
		for (char c : input.toCharArray())
		{
			if (curr.next(c) != null)
			{
				curr = curr.next(c);
			}
			else
			{
				break;
			}
		}
		return curr.getValue(input);
	}
	
	/** Inserts a word into the trie
	 * 	@param word - The string being inserted into the trie
	 * 	@param id - The enum this string is tied to
	 * 	@param root - A reference to the root node */
	public static <T> void insertWord (String word, T id, TrieNode <T> root)
	{
		word = word.toLowerCase();
		TrieNode <T> curr = root;
		for (char c : word.toCharArray())
		{
			curr.addChild(c, id);
			curr = curr.next(c);
		}
	}
}
