package com.joojet.biblefetcher.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;

public class APIParser 
{
	/** Fetches biblical content from the API and extracts the content body from the response 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws MalformedURLException */
	public static String parseChapter (BibleID translation, BookID book, int chapter) throws MalformedURLException, ProtocolException, IOException
	{
		StringBuilder content = new StringBuilder (APIFetcher.fetchChapter(translation, book, chapter));
		
		JsonParser parser = new JsonParser ();
		JsonElement tree = parser.parse(content.toString());
		
		if (tree.isJsonObject())
		{
			JsonObject parsed = tree.getAsJsonObject();
			if (translation.equals(BibleID.ESV))
			{
				System.out.println (parsed.get("passages").isJsonArray());
				JsonArray arr = (JsonArray) parsed.get("passages");
				return fixContent (trimContent (arr.get(0).getAsString()));
			}
			
			return fixContent (trimContent (((JsonObject) parsed.get("data")).get("content").toString()));
		}
		return content.toString();
	}
	
	/** Trims quotation and new line characters from the content string. */
	public static String trimContent (String content)
	{
		return content.replaceAll("([\\\\][n])", "").replaceAll("([\\\"])", "").replaceAll("([\\s][\\s]+)", " ").trim();
	}
	
	/** Fixes common issues that occurs within text extracted from the API */
	public static String fixContent (String content)
	{
		return content.replaceAll("([â][€][™])", "\'").replaceAll("([â][€][˜])", "\'").replaceAll("([Â][¶][\\s])","").replaceAll("([Ã])", "a").replaceAll("([¦])", "e").replaceAll("([â][€][”])", "—").replaceAll("([â][€][\\s\\S])", "\"").replaceAll("([Â][//s//S]+)", "");
	}
}
