package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.*;

import org.junit.jupiter.api.Test;

class TestSingleChapter {

	/** Tests to see if the following single chapter books returns the entire book and not just a
	 *  single verse. */
	@Test
	void testSingleChapter() 
	{
		testPassage (BookID.JN2);
		testPassage (BookID.JN3);
		testPassage (BookID.JUD);
		testPassage (BookID.OBA);
		testPassage (BookID.PHN);
	}
	
	private void testPassage (BookID book)
	{
		try 
		{
			ArrayList <String> content = BibleFetcher.getVerses(BibleID.ESV, book, 1);
			System.out.println (content.toString());
			assertEquals (content.size() > 1, true);
		} 
		catch (Exception e) 
		{
			System.out.println ("FAIL: " + e.getMessage());
			fail (e.getMessage());
		} 
		
	}

}
