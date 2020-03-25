package main;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import constants.BibleID;
import constants.BookID;
import fetcher.BibleFetcher;
import interpreter.CommandInterpreter;

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
			
			fetchVerses(BibleID.KJV, BookID.REV, 1);
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
