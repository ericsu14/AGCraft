package com.joojet.plugins.biblefetcher.string;

import java.util.ArrayList;

public class ContentParser 
{
	/** Merges a list of passages into one large passage */
	public static String mergeContent (ArrayList <String> list)
	{
		StringBuilder content = new StringBuilder ();
		
		for (String s : list)
		{
			content.append(s + "\n");
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
						result.add(appendDash (substr));
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
				result.add(((i + 1) - start == 0) ? curr :  i + ". " + curr );
			}
			++i;
		}
		
		return result;
	}
	
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
