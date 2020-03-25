package main;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import constants.BibleID;
import constants.BookID;
import parser.BibleParser;

public class Test {

	public static void main(String[] args) 
	{
		ArrayList <String> container;
		try 
		{
			container = BibleParser.getVerses(BibleID.WEB, BookID.LUK, 1);
			
			for (String s : container)
			{
				System.out.println (s);
			}
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		
	}

}
