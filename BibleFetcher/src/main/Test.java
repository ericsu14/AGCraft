package main;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import constants.BibleID;
import constants.BookID;
import fetcher.BibleFetcher;

public class Test {
	
	public static void fetchVerses (BibleID translation, BookID book, int chapter) throws MalformedURLException, ProtocolException, RuntimeException, IOException
	{
		ArrayList <String> container = BibleFetcher.getVerses(translation, book, chapter);
		for (String s : container)
		{
			System.out.println (s);
		}
		System.out.println ("----------");
	}
	
	public static void fetchVerses (BibleID translation, BookID book, int chapter, int to, int from) throws MalformedURLException, ProtocolException, RuntimeException, IOException
	{
		ArrayList <String> container = BibleFetcher.getVerses(translation, book, chapter, to, from);
		for (String s : container)
		{
			System.out.println (s);
		}
		System.out.println ("----------");
	}

	public static void main(String[] args)
	{
		try 
		{			
			fetchVerses(BibleID.ASV, BookID.ROM, 12, 2, 4);
			fetchVerses(BibleID.ASV, BookID.ROM, 12);
			fetchVerses(BibleID.KJV, BookID.ROM, 12);
			fetchVerses(BibleID.WEB, BookID.ROM, 12);
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
