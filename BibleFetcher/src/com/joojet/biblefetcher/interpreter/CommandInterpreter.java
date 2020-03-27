package com.joojet.biblefetcher.interpreter;

import java.util.Arrays;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;

public class CommandInterpreter 
{
	TrieNode <BibleID> bibRoot;
	TrieNode <BookID> bookRoot;
	
	public CommandInterpreter ()
	{
		this.bibRoot = new TrieNode <BibleID> (' ', null);
		this.bookRoot = new TrieNode <BookID> (' ', null);
		
		this.populateBibleIDTrie();
		this.populateBookIDTrie();
	}
	
	/** Searches the bible trie for the specified input and
	 *  returns the BibleID enum that holds the closest approximation
	 *  to the search term. Returns null if not found.
	 *  	@param input - Search term */
	public BibleID searchBibleTrie (String input)
	{
		return (BibleID) this.searchTrie(input, this.bibRoot);
	}
	
	/** Searches the book trie for the specified input and
	 *  returns the BookID enum that holds the closest approximation
	 *  to the search term. Returns null if not found.
	 *  	@param input - Search term */
	public BookID searchBookTrie (String input)
	{
		return (BookID) this.searchTrie(input, this.bookRoot);
	}
	
	/** Populates the bible search trie by adding every possible way a user could search for their desired bible translation */
	private void populateBibleIDTrie ()
	{
		Arrays.stream(BibleID.values()).forEach(entry -> {
			this.insertWord(entry.getBibleID(), entry, this.bibRoot);
			this.insertWord(entry.name(), entry, this.bibRoot);
			this.insertWord(entry.getTranslationName().replaceAll("([ ])", ""), entry, this.bibRoot);
		});
	}
	
	/** Populates the book search trie by adding every possible way a used could search for their desired book chapter */
	private void populateBookIDTrie ()
	{
		Arrays.stream(BookID.values()).forEach(entry -> {
			this.insertWord(entry.getID(), entry, this.bookRoot);
			this.insertWord(entry.name(), entry, this.bookRoot);
			if (entry.getChapter() == 0)
			{
				this.insertWord(entry.getTitle().replaceAll("([ ])", ""), entry, this.bookRoot);
			}
			/* If this book does come in multiple chapters (or parts), allow the user to search for
			 * this book with the part number appended in front or behind of the title. */
			else
			{
				this.insertWord(entry.getChapter() + entry.getTitle(), entry, this.bookRoot);
				this.insertWord(entry.getTitle() + entry.getChapter(), entry, this.bookRoot);
			}
		});
	}
	
	/** Searches the trie using the input String and returns the linkedID 
	  * associated with that search term.
	  * @param input - Search term
	  * @param root - Pointer to root node of the trie being searched
	  * @return - The linkedID associated with the search term, or null if not found.*/
	private <T> T searchTrie (String input, TrieNode <T> root)
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
		return curr.getLinkedID(input);
	}
	
	/** Inserts a word into the trie
	 * 	@param word - The string being inserted into the trie
	 * 	@param id - The enum this string is tied to
	 * 	@param root - A reference to the root node */
	private <T> void insertWord (String word, T id, TrieNode <T> root)
	{
		word = word.toLowerCase();
		TrieNode <T> curr = root;
		for (char c : word.toCharArray())
		{
			if (curr.next(c) == null)
			{
				curr.addChild(c, id);
			}
			else
			{
				curr.next(c).updateFrequency(id);
			}
			curr = curr.next(c);
		}
	}
	
}
