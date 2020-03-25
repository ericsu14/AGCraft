package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import constants.*;
import interpreter.CommandInterpreter;

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
	void testValidBookNameSearches ()
	{
		for (BookID id : BookID.values())
		{
			assertEquals (interpreter.searchBookTrie(removeWhiteSpace(id.getFormattedTitle())), id);
		}
	}
	
	/** Helper function used to filter out whitespaces from string */
	private String removeWhiteSpace (String input)
	{
		return input.replaceAll("([\\s]+)", "");
	}
	
}
