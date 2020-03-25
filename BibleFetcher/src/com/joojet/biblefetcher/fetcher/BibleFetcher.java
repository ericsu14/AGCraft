package com.joojet.biblefetcher.fetcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import com.joojet.biblefetcher.api.APIParser;
import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.database.DatabaseManager;

public class BibleFetcher 
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
	
	/** Extracts an entire chapter from the specified chapter of the bible. */
	public static ArrayList <String> getVerses (BibleID translation, BookID book, int chapter) throws MalformedURLException, ProtocolException, IOException
	{
		// TODO: Check against database to see if that section of the bible is already cached
		String content = DatabaseManager.fetch(translation, book, chapter);
		if (content.isEmpty())
		{
			content = APIParser.parseChapter(translation, book, chapter);
			DatabaseManager.insert(translation, book, chapter, content);
			System.out.println ("Database updated");
		}
		
		return tokenizeChapter (content);
	}
	
	/** Extracts an entire chapter from the specified chapter of the bible starting from a specific verse number 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws MalformedURLException */
	public static ArrayList <String> getVerses (BibleID translation, BookID book, int chapter, int verseStart) throws MalformedURLException, ProtocolException, IOException
	{
		ArrayList <String> tokens = getVerses (translation, book, chapter);
		
		int size = tokens.size();
		
		if (!inRange (1, size, verseStart))
		{
			throw new RuntimeException ("Verse ranges are out of bounds. There are " + size + " verses in the book of " + book.getFormattedTitle() + " " + chapter + "."); 
		}
		return parseList (tokens, verseStart, size);
	}
	
	/** Extracts a set of verses from the specified chapter of the bible 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws MalformedURLException */
	public static ArrayList <String> getVerses (BibleID translation, BookID book, int chapter, int verseStart, int verseEnd) throws RuntimeException, MalformedURLException, ProtocolException, IOException
	{	
		ArrayList<String> tokens = getVerses (translation, book, chapter);

		int size = tokens.size();
		
		if (verseStart > verseEnd)
		{
			throw new RuntimeException ("Invalid range.");
		}
		
		if (!inRange (1, size, verseStart) || !inRange (1, size, verseEnd))
		{
			throw new RuntimeException ("Verse ranges are out of bounds. There are " + size + " verses in the book of " + book.getFormattedTitle() + " " + chapter + ".");
		}
		
		return parseList (tokens, verseStart, verseEnd);
	}
	
	private static boolean inRange (int x, int y, int target)
	{
		return (x <= target && target <= y);
	}
	
	/** Returns a subset of an ArrayList.
	 * 		@param list - Original list
	 * 		@param start - Starting index
	 * 		@param end - Ending index */
	private static ArrayList <String> parseList (ArrayList<String> list, int start, int end)
	{
		ArrayList <String> sublist = new ArrayList <String> ();
		for (int i = start - 1; i <= end - 1; ++i)
		{
			sublist.add(list.get(i));
		}
		return sublist;
	}
}
