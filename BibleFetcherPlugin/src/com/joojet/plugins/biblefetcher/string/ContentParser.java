package com.joojet.plugins.biblefetcher.string;

import java.util.ArrayList;

public class ContentParser 
{
	/** Merges a list of passages into one large passage */
	public static String mergeContent (ArrayList <String> list)
	{
		StringBuilder content = new StringBuilder ();
		
		boolean firstPass = false;
		for (String s : list)
		{
			if (!firstPass)
			{
				content.append(s + "\n");
				firstPass = true;
			}
			else
			{
				content.append(s + " ");
			}
		}
		return content.toString();
	}
	
	/** Splits a large string into multiple pages */
	public static ArrayList <String> formatContent (String content, int start)
	{
		ArrayList <String> temp = new ArrayList <String> ();
		temp.add(content);
		return formatContent (temp, start);
	}
	
	/** Splits a passage into two parts should it exceed character MC book's character limits */
	public static ArrayList <String> formatContent (ArrayList <String> list, int start)
	{
		int maxLength = 246;
		ArrayList <String> result = new ArrayList <String> ();
		
		int i = start - 1;
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
						result.add(appendIndex (appendDash (substr), i, start));
						firstLine = false;
					}
					else
					{
						if (lastElement)
						{
							result.add(substr);
							break;
						}
						else
						{
							result.add(appendDash (substr));
						}
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
				result.add(appendIndex (curr, i, start));
			}
			++i;
		}
		
		return result;
	}
	
	/** Appends a passage index in front of the string if the index is not the content header */
	private static String appendIndex (String s, int i, int start)
	{
		StringBuilder str = new StringBuilder ();
		if (!((i + 1) - start == 0))
		{
			str.append (i);
			str.append(". ");
		}
		str.append(s);
		return str.toString();
	}
	
	/** Appends a dash to the end of a string if the last character of that string is an alphanumeric character. */
	private static String appendDash (String s)
	{
		StringBuilder str = new StringBuilder (s);
		char lastChar = s.charAt(s.length() - 1);
		if (lastChar != '?' && lastChar != ' ' && lastChar != '.' && lastChar != ',' && lastChar != '”' && lastChar != '\"' && lastChar != '\n'
				&& !Character.isWhitespace(lastChar) && !(lastChar >= 48 && lastChar <= 57))
		{
			str.append("-");
		}
		
		return str.toString();
	}
}
