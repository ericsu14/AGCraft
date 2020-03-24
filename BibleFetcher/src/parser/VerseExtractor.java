package parser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import constants.BibleID;
import constants.BookID;

public class VerseExtractor 
{
	
	public static ArrayList <String> tokenizeChapter (String content) throws UnsupportedEncodingException
	{
		ArrayList <String> container = new ArrayList <String> ();
		String[] tokens = content.split("(\\[.[0-9]*\\])");
		
		for (String curr : tokens)
		{
			curr = curr.trim();
			// Ignores empty lines
			if (!curr.isEmpty())
			{
				container.add(curr.trim());
			}
		}
		
		return container;
	}
	
	public static ArrayList <String> getVerses (BibleID translation, BookID book, int chapter) throws UnsupportedEncodingException
	{
		// TODO: Check against database to see if that section of the bible is already cached
		String content = APIParser.parseChapter(translation, book, chapter);
		
		return tokenizeChapter (content);

	}
	
	/** Extracts a set of verses from the specified chapter of the bible */
	public static ArrayList <String> getVerses (BibleID translation, BookID book, int chapter, int verseStart, int verseEnd) throws UnsupportedEncodingException, RuntimeException
	{	
		ArrayList<String> tokens = getVerses (translation, book, chapter);

		int size = tokens.size();
		
		if (!inRange (1, size, verseStart) || !inRange (1, size, verseEnd))
		{
			throw new RuntimeException ("Specified verses do not exist.");
		}
		
		ArrayList <String> sublist = new ArrayList <String> ();
		
		for (int i = verseStart - 1; i < verseEnd - 1; ++i)
		{
			sublist.add(tokens.get(i));
		}
		
		return sublist;
	}
	
	public static boolean inRange (int x, int y, int target)
	{
		return (x <= target && target <= y);
	}
}
