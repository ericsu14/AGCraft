package com.joojet.biblefetcher.main;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;

public class Test {
	
	public static void fetchVerses (BibleID translation, BookID book, int chapter) throws MalformedURLException, ProtocolException, RuntimeException, IOException
	{
		ArrayList <String> container = BibleFetcher.getVerses(translation, book, chapter);
		
		System.out.println (book.getFormattedTitle() + " " + chapter + " (" + translation.getBibleID() + ")");
		int i = 1;
		for (String s : container)
		{
			System.out.println (i++ + ". " + s);
		}
		System.out.println ("----------");
	}
	
	public static void fetchVerses (BibleID translation, BookID book, int chapter, int to) throws MalformedURLException, ProtocolException, RuntimeException, IOException
	{
		ArrayList <String> container = BibleFetcher.getVerses(translation, book, chapter, to);
		
		System.out.println (book.getFormattedTitle() + " " + chapter + ":" + to + "-" + (container.size() + (to - 1)) + " (" + translation.getBibleID() + ")");
		int i = to;
		for (String s : container)
		{
			System.out.println (i++ + ". " + s);
		}
		System.out.println ("----------");
	}
	
	public static void fetchVerses (BibleID translation, BookID book, int chapter, int to, int from) throws MalformedURLException, ProtocolException, RuntimeException, IOException
	{
		ArrayList <String> container = BibleFetcher.getVerses(translation, book, chapter, to, from);
		System.out.println (book.getFormattedTitle() + " " + chapter + ":" + to + "-" + from + " (" + translation.getBibleID() + ")");
		int i = to;
		for (String s : container)
		{
			System.out.println (i++ + ". " + s);
		}
		System.out.println ("----------");
	}

	public static void main(String[] args)
	{
		try 
		{
			CommandInterpreter interpreter = new CommandInterpreter ();
			BookID test = interpreter.searchBookTrie("number");
			System.out.println (test.getTitle());
			
			fetchVerses(BibleID.KJV, BookID.REV, 8, 1);
			fetchVerses(BibleID.KJV, BookID.REV, 9);
			fetchVerses(BibleID.KJV, BookID.REV, 10, 1);
			fetchVerses(BibleID.WEB, BookID.JOB, 11);
			fetchVerses(BibleID.FBVNT, BookID.ROM, 12);
			fetchVerses(BibleID.KJV, BookID.ROM, 12);
			fetchVerses(BibleID.KJV, BookID.MRK, 12);
			fetchVerses(BibleID.KJV, BookID.MRK, 1);
			fetchVerses(BibleID.KJV, BookID.MRK, 2);
			fetchVerses(BibleID.FBVNT, BookID.MRK, 2);
			fetchVerses(BibleID.FBVNT, BookID.MRK, 1);
			fetchVerses(BibleID.FBVNT, BookID.JN3, 1);
			fetchVerses(BibleID.KJV, BookID.MRK, 3);
			fetchVerses(BibleID.KJV, BookID.MRK, 5);
			fetchVerses (BibleID.KJV, BookID.MRK, 19);
		} 
		catch (IOException e) 
		{
			System.out.println ("That chapter of the bible is not supported in this version of the book.");
		}
		
		catch (Exception e)
		{
			System.out.println (e.getMessage());
		}
		
	}

}
