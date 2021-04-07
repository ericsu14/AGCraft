package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.bukkit.ChatColor;
import org.junit.jupiter.api.Test;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;
import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.biblefetcher.string.ContentParser;

class TestFunctions {

	
	/** Tests the format content function, which should split up long passages into multiple 253 character chunks should
	 *  the passage exceed 255 characters. */
	@Test
	void testFormatContent() 
	{
		try 
		{
			List <String> verses = BibleFetcher.getVerses(BibleID.KJV, BookID.REV, 11, 18, 18);
			List <String> split = ContentParser.formatContent (verses, 1);
			assertEquals (split.size(), 2);
			vertifyCharacterLimits (split);
		} 
		catch (Exception e)
		{
			e.getStackTrace();
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
			List <String> verses = BibleFetcher.getVerses(BibleID.KJV, BookID.EST, 8, 9, 9);
			List <String> split = ContentParser.formatContent (verses, 1);
			assertEquals (split.size(), 3);
			vertifyCharacterLimits (split);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFormatContent3 ()
	{
		try
		{
			List <String> verses = BibleFetcher.getVerses(BibleID.KJV, BookID.MRK, 1);
			verses.add(0, "Header");
			String bigPassage = ContentParser.mergeContent(ContentParser.formatContent(verses, 1));
			List <String> pages = ContentParser.formatContent(bigPassage, 1);
			vertifyCharacterLimits (pages);
		}
		
		catch (Exception e)
		{
			e.getStackTrace();
			fail (e.getMessage());
		}
	}
	
	/** Tests to see if alternating character and word colors work correctly on the alternateTextColors function*/
	@Test
	void testAlternateTextColorsCharacter ()
	{
		String attempt = StringUtil.alternateTextColors("USC", TextPattern.CHARACTER, ChatColor.RED, ChatColor.GOLD);
		String expected = ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C";
		
		assertEquals (attempt, expected);
		
		attempt = StringUtil.alternateTextColors("American Flag", TextPattern.CHARACTER, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		expected = ChatColor.RED + "A" + ChatColor.WHITE + "m" + ChatColor.BLUE + "e" + ChatColor.RED + "r"
				+ ChatColor.WHITE + "i" + ChatColor.BLUE + "c" + ChatColor.RED + "a" + ChatColor.WHITE + "n" + " "
				+ ChatColor.BLUE + "F" + ChatColor.RED + "l" + ChatColor.WHITE + "a" + ChatColor.BLUE + "g";
		assertEquals (attempt, expected);
		
		attempt = StringUtil.alternateTextColors("American Flag Thing", TextPattern.WORD, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		expected = ChatColor.RED + "American" + " " + ChatColor.WHITE + "Flag" + " " + ChatColor.BLUE + "Thing";
		assertEquals (attempt, expected);
		
		attempt = StringUtil.alternateTextColors("USC IS BEST", TextPattern.WORD, ChatColor.RED, ChatColor.GOLD);
		expected = ChatColor.RED + "USC" + " " + ChatColor.GOLD + "IS" + " " + ChatColor.RED + "BEST";
		assertEquals (attempt, expected);
	}
	
	
	/** Checks if each item in the array is below 255 characters */
	void vertifyCharacterLimits (List <String> list)
	{
		for (String s : list)
		{
			System.out.println (s);
			assertEquals ((s.length() <= 255), true);
		}
	}
	
}
