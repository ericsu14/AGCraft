package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;
import com.joojet.plugins.biblefetcher.string.ContentParser;

class TestFunctions {

	
	/** Tests the format content function, which should split up long passages into multiple 253 character chunks should
	 *  the passage exceed 255 characters. */
	@Test
	void testFormatContent() 
	{
		try 
		{
			ArrayList <String> verses = BibleFetcher.getVerses(BibleID.KJV, BookID.REV, 11, 18, 18);
			ArrayList <String> split = ContentParser.formatContent (verses, 1);
			assertEquals (split.size(), 2);
			vertifyCharacterLimits (split);
		} 
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	/** Tests the format content function, which should split up long passages into multiple 253 character chunks should
	 *  the passage exceed 255 characters. */
	@Test
	void testFormatContent2() 
	{
		try 
		{
			// Known as the longest passage in the bible
			ArrayList <String> verses = BibleFetcher.getVerses(BibleID.KJV, BookID.EST, 8, 9, 9);
			ArrayList <String> split = ContentParser.formatContent (verses, 1);
			assertEquals (split.size(), 3);
			vertifyCharacterLimits (split);
		} 
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFormatContent3 ()
	{
		try
		{
			ArrayList <String> verses = BibleFetcher.getVerses(BibleID.ESV, BookID.MRK, 8);
			verses.add(0, "Header");
			String bigPassage = ContentParser.mergeContent(ContentParser.formatContent(verses, 1));
			ArrayList <String> pages = ContentParser.formatContent(bigPassage, 1);
			vertifyCharacterLimits (pages);
		}
		
		catch (Exception e)
		{
			fail (e.getMessage());
		}
	}
	
	/** Checks if each item in the array is below 255 characters */
	void vertifyCharacterLimits (ArrayList <String> list)
	{
		for (String s : list)
		{
			System.out.println (s);
			assertEquals ((s.length() <= 255), true);
		}
	}
	
}
