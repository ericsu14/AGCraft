package interpreter;

import java.util.Arrays;

import constants.BibleID;
import constants.BookID;

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
	
	/** Populates the bible search trie by adding every possible way a user could search for their desired bible translation */
	public void populateBibleIDTrie ()
	{
		Arrays.stream(BibleID.values()).forEach(entry -> {
			this.insertWord(entry.getBibleID().toLowerCase(), entry, this.bibRoot);
			this.insertWord(entry.name().toLowerCase(), entry, this.bibRoot);
			this.insertWord(entry.getTranslationName().toLowerCase(), entry, this.bibRoot);
		});
	}
	
	/** Populates the book search trie by adding every possible way a used could search for their desired book chapter */
	public void populateBookIDTrie ()
	{
		Arrays.stream(BookID.values()).forEach(entry -> {
			this.insertWord(entry.getID().toLowerCase(), entry, this.bookRoot);
			this.insertWord(entry.name().toLowerCase(), entry, this.bookRoot);
			if (entry.getChapter() == 0)
			{
				this.insertWord(entry.getTitle().toLowerCase(), entry, this.bookRoot);
			}
			/* If this book does come in multiple chapters (or parts), allow the user to search for
			 * this book with the part number appended in front or behind of the title. */
			else
			{
				this.insertWord(entry.getChapter() + entry.getTitle().toLowerCase(), entry, this.bookRoot);
				this.insertWord(entry.getTitle().toLowerCase() + entry.getChapter(), entry, this.bookRoot);
			}
		});
	}
	
	/** Inserts a word into the trie
	 * 	@param word - The string being inserted into the trie
	 * 	@param id - The enum this string is tied to
	 * 	@param root - A reference to the root node */
	public <T> void insertWord (String word, T id, TrieNode <T> root)
	{
		TrieNode <T> curr = root;
		for (char c : word.toCharArray())
		{
			if (curr.next(c) == null)
			{
				curr.addChild(c, id);
			}
			else
			{
				curr.incrementCount();
			}
			curr = curr.next(c);
		}
	}
	
	/** Searches the bible trie for the specified input and
	 *  returns the BibleID enum that holds the closest approximation
	 *  to the search term. Returns null if not found.
	 *  	@param input - Search term */
	public BibleID searchBibleTrie (String input)
	{
		input = input.toLowerCase().trim();
		
		TrieNode<BibleID> curr = bibRoot;
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
		return (BibleID) curr.getLinkedID();
	}
	
	/** Searches the book trie for the specified input and
	 *  returns the BookID enum that holds the closest approximation
	 *  to the search term. Returns null if not found.
	 *  	@param input - Search term */
	public BookID searchBookTrie (String input)
	{
		input = input.toLowerCase().trim();
		
		TrieNode<BookID> curr = bookRoot;
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
		return (BookID) curr.getLinkedID();
	}
	
	
}
