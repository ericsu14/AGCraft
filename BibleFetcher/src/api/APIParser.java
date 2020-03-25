package api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import constants.BibleID;
import constants.BookID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIParser 
{
	/** Fetches biblical content from the API and extracts the content body from the response 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws MalformedURLException */
	public static String parseChapter (BibleID translation, BookID book, int chapter) throws MalformedURLException, ProtocolException, IOException
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
		return content.replaceAll("([â][€][™])", "\'").replaceAll("([Ã])", "a").replaceAll("([¦])", "e").replaceAll("([â][€][”])", "—").replaceAll("([â][€][\\s\\S])", "\"");
	}
}
