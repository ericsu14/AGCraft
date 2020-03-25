package fetcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import api.APIParser;
import constants.BibleID;
import constants.BookID;
import database.DatabaseManager;

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
			throw new RuntimeException ("Specified verses do not exist.");
		}
		
		ArrayList <String> sublist = new ArrayList <String> ();
		
		for (int i = verseStart - 1; i <= verseEnd - 1; ++i)
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
