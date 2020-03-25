package main;
import java.io.IOException;
import java.util.ArrayList;

import constants.BibleID;
import constants.BookID;
import fetcher.BibleFetcher;

public class Test {

	public static void main(String[] args)
	{
		ArrayList <String> container;
		try 
		{
			container = BibleFetcher.getVerses(BibleID.WEB, BookID.ROM, 12);
			
			for (String s : container)
			{
				System.out.println (s);
			}
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
