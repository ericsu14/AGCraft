package parser;

import java.io.UnsupportedEncodingException;

import api.APIFetcher;
import constants.BibleID;
import constants.BookID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BibleParser 
{
	/** Fetches biblical content from the API and extracts the content body from the response */
	public static String parseChapter (BibleID translation, BookID book, int chapter) throws UnsupportedEncodingException
	{
		StringBuilder content = new StringBuilder (APIFetcher.fetchChapter(translation, book, chapter));
		
		JsonElement tree = JsonParser.parseString(content.toString());
		
		if (tree.isJsonObject())
		{
			JsonObject parsed = tree.getAsJsonObject();
			return fixContent (trimContent (((JsonObject) parsed.get("data")).get("content").toString()));
		}
		return content.toString();
	}
	
	/** Trims quotation and new line characters from the content string. */
	public static String trimContent (String content)
	{
		return content.replaceAll("([\\\\][n])", "").replaceAll("([\\\"])", "").trim();
	}
	
	/** Fixes common issues that occurs within text extracted from the API */
	public static String fixContent (String content)
	{
		return content.replaceAll("([�][�][�])", "\'").replaceAll("([�])", "a").replaceAll("([�])", "e").replaceAll("([�][�][�])", "�").replaceAll("([�][�][\\s\\S])", "\"");
	}
}
