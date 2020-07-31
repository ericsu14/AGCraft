package com.joojet.biblefetcher.interpreter;

import java.util.Arrays;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;

public class BibleCommandInterpreter 
{
	TrieNode <BibleID> bibRoot;
	TrieNode <BookID> bookRoot;
	
	public BibleCommandInterpreter ()
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
		return (BibleID) TrieUtil.searchTrie(input, this.bibRoot);
	}
	
	/** Searches the book trie for the specified input and
	 *  returns the BookID enum that holds the closest approximation
	 *  to the search term. Returns null if not found.
	 *  	@param input - Search term */
	public BookID searchBookTrie (String input)
	{
		return (BookID) TrieUtil.searchTrie(input, this.bookRoot);
	}
	
	/** Populates the bible search trie by adding every possible way a user could search for their desired bible translation */
	private void populateBibleIDTrie ()
	{
		Arrays.stream(BibleID.values()).forEach(entry -> {
			TrieUtil.insertWord(entry.getBibleID(), entry, this.bibRoot);
			TrieUtil.insertWord(entry.name(), entry, this.bibRoot);
			TrieUtil.insertWord(entry.getTranslationName().replaceAll("([ ])", ""), entry, this.bibRoot);
		});
	}
	
	/** Populates the book search trie by adding every possible way a used could search for their desired book chapter */
	private void populateBookIDTrie ()
	{
		Arrays.stream(BookID.values()).forEach(entry -> {
			TrieUtil.insertWord(entry.getID(), entry, this.bookRoot);
			TrieUtil.insertWord(entry.name(), entry, this.bookRoot);
			
			/* If this book does come in multiple chapters (or parts), allow the user to search for
			 * this book with the part number appended in front or behind of the title. */
			if (entry.getChapter() == 0)
			{
				TrieUtil.insertWord(entry.getTitle(), entry, this.bookRoot);
			}
			else
			{
				TrieUtil.insertWord(entry.getChapter() + entry.getTitle(), entry, this.bookRoot);
				TrieUtil.insertWord(entry.getTitle() + entry.getChapter(), entry, this.bookRoot);
			}
		});
	}
	
}
