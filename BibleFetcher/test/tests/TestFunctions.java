package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;

class TestFunctions {

	
	/** Tests the format content function, which should split up long passages into multiple 253 character chunks should
	 *  the passage exceed 253 characters. */
	@Test
	void testFormatContent() 
	{
		try 
		{
			ArrayList <String> verses = BibleFetcher.getVerses(BibleID.KJV, BookID.REV, 11, 18, 18);
			ArrayList <String> split = formatContent (verses);
			assertEquals (split.size(), 2);
			vertifyCharacterLimits (split);
		} 
		catch (Exception e)
		{
			assertEquals (1, 4);
		}
	}
	
	/** Tests the format content function, which should split up long passages into multiple 253 character chunks should
	 *  the passage exceed 253 characters. */
	@Test
	void testFormatContent2() 
	{
		try 
		{
			// Known as the longest passage in the bible
			ArrayList <String> verses = BibleFetcher.getVerses(BibleID.ESV, BookID.EST, 8, 9, 9);
			ArrayList <String> split = formatContent (verses);
			assertEquals (split.size(), 2);
			vertifyCharacterLimits (split);
		} 
		catch (Exception e)
		{
			System.err.println (e.getMessage());
			assertEquals (1, 2);
		}
	}
	
	/** Checks if each item in the array is below 255 characters */
	void vertifyCharacterLimits (ArrayList <String> list)
	{
		for (String s : list)
		{
			assertEquals ((s.length() <= 255), true);
		}
	}
	
	/** Splits a passage into two parts should it exceed character MC book's character limits */
	private ArrayList <String> formatContent (ArrayList <String> list)
	{
		int maxLength = 250;
		ArrayList <String> result = new ArrayList <String> ();
		
		int i = 1;
		boolean firstLine = true;
		boolean lastElement = false;
		for (String curr : list)
		{
			firstLine = true;
			lastElement = false;
			if (curr.length() > maxLength)
			{
				int startIndex = 0;
				int endIndex = maxLength - 1;
				
				String substr;
				while (endIndex <= curr.length() - 1)
				{
					substr = curr.substring(startIndex, endIndex);
					if (firstLine)
					{
						result.add(i + ". " + substr);
						firstLine = false;
					}
					else
					{
						result.add(substr);
					}
					if (lastElement)
					{
						break;
					}
					startIndex = endIndex;
					endIndex += maxLength;
					
					if (endIndex > curr.length() - 1)
					{
						endIndex = curr.length() - 1;
						lastElement = true;
					}
				}
				
			}
			else
			{
				result.add(i + ". " + curr);
			}
			++i;
		}
		
		return result;
	}
}
