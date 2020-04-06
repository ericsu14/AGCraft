package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.joojet.biblefetcher.constants.*;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;

class TestInterpreter 
{

	private final CommandInterpreter interpreter = new CommandInterpreter ();
	
	
	/** Tests the command interpreter by feeding it with valid bible IDs */
	@Test
	void testValidBibleIDSearches ()
	{
		for (BibleID id : BibleID.values())
		{
			assertEquals (interpreter.searchBibleTrie(id.getBibleID()), id);
		}
	}
	
	/** Tests the command interpreter by feeding it with valid bible translation names */
	@Test
	void testValidBibleIDTranslationNames ()
	{
		for (BibleID id : BibleID.values())
		{
			assertEquals (interpreter.searchBibleTrie(removeWhiteSpace (id.getTranslationName())), id);
		}
	}
	
	/** Tests the command interpreter by feeding it with valid book IDs */
	@Test
	void testValidBookIDSearches ()
	{
		for (BookID id : BookID.values())
		{
			assertEquals (interpreter.searchBookTrie(id.getID()), id);
		}
	}
	
	/** Tests the command interpreter by feeding it with valid book names */
	@Test
	void testValidBookNameSearches ()
	{
		for (BookID id : BookID.values())
		{
			assertEquals (interpreter.searchBookTrie(removeWhiteSpace(id.getFormattedTitle())), id);
		}
	}
	
	/** For BookIDs with chapter information, test the command interpreter by seeing if it could search for every possible arrangement
	 *  of title + chapter. For example, John1 will be tested as 1John and John1. */
	@Test
	void testValidBookNameWithChapterSearches ()
	{
		for (BookID id : BookID.values())
		{
			if (id.getChapter() > 0)
			{
				String prefix = id.getChapter() + id.getTitle();
				String postfix = id.getTitle() + id.getChapter();
				
				assertEquals (interpreter.searchBookTrie(prefix), id);
				assertEquals (interpreter.searchBookTrie(postfix), id);
			}
		}
	}
	
	/** Tests the command interpreter by feeding it with commonly misspelled book titles */
	@Test
	void testMisspelledBookNameSearches ()
	{
		testBookInput ("markz", BookID.MRK);
		testBookInput ("marks", BookID.MRK);
		testBookInput ("ezkieal", BookID.EZK);
		testBookInput ("ezraro", BookID.EZR);
		testBookInput ("genasis", BookID.GEN);
		testBookInput ("jonaes", BookID.JON);
		testBookInput ("revalations", BookID.REV);
		testBookInput ("revelation", BookID.REV);
		testBookInput ("revaleations", BookID.REV);
		// TODO: The rest
	}
	
	/** Helper function used to test if a BookID returned from a custom input 
	 *  matches with its intended target */
	private void testBookInput (String input, BookID expected)
	{
		assertEquals (interpreter.searchBookTrie(removeWhiteSpace (input)), expected);
	}
	
	/** Helper function used to filter out whitespaces from string */
	private String removeWhiteSpace (String input)
	{
		return input.replaceAll("([\\s]+)", "");
	}
	
}
