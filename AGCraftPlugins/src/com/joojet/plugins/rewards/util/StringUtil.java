package com.joojet.plugins.rewards.util;

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
    
}
