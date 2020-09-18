package com.joojet.plugins.agcraft.util;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.enums.TextPattern;

public class StringUtil 
{
	
	/** Formats a typical enum name to be user-friendly.
	 *  This is done through converting underscores (or any other regex defined by split) to spaces
	 *  and only capitalizing the first letter of each word.
	 * 	 @param str - String to be converted
	 * 	 @param split - The regex used to split the string */
	public static String generateReadableName (String str, String split)
	{
		String rawItemName = str;
		String[] tokens = rawItemName.split(split);
		StringBuilder name = new StringBuilder ();
		for (String token : tokens)
		{
			name.append(capitalizeString(token));
			name.append(" ");
		}
		
		return name.toString().trim();
	}
	
    /** Capitalizes only the first letter of the string and
     * 	leaves the rest lowercase
     * 		@param str - String to be converted */
    public static String capitalizeString (String str)
    {
    	StringBuilder string = new StringBuilder (str.toLowerCase());
    	string.replace(0, 1, str.toUpperCase().substring(0, 1));
    	return string.toString();
    }
    
    /** Applies an alternating color on each word or character
     *  on the passed String.
     *  	@param str - String to be colored
     *  	@param pattern - Specifies how colors should be applied onto the string
     *  	@param colors - A list of ChatColors to be applied into the string.  */
    public static String alternateTextColors (String str, TextPattern pattern, ChatColor... colors)
    {
    	StringBuilder result = new StringBuilder ();
    	String tokens[] = str.split(" ");
    	
    	int colorIndex = 0;
    	ChatColor color = colors[colorIndex];
    	char currentChar;
    	
    	for (String word : tokens)
    	{
    		for (int i = 0; i < word.toCharArray().length; ++i)
    		{
    			currentChar = word.charAt(i);
    			// If we are at the first index of the word
    			// or if the pattern is set to alternating characters,
    			// append the current color before the character
    			// and grab the next color
    			if (i == 0 || pattern == TextPattern.CHARACTER)
    			{
    				result.append(color);
    				colorIndex = ((colorIndex + 1) < colors.length) ? colorIndex + 1 : 0;
    				color = colors[colorIndex];
    			}
    			result.append(currentChar);
    		}
    		result.append(" ");
    	}
    	
    	return result.toString().trim();
    }
}
