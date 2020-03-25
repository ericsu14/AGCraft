package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import constants.*;

public class APIFetcher 
{
	private static final String kAPIURL = "https://api.scripture.api.bible/v1";
	private static final String kAPIKey = "c83f0ad75d6f512a24112697f36e8e42";
	
	
	/** Uses call /v1/bibles/{bibleId}/passages/{passageId} to retrieve an entire chapter from the bible.
	 *  	@param translation - The version of the bible we are using
	 *  	@param book - The book of the bible we are reading at
	 *  	@param chapter - The chapter of the book we are reading at
	 *  	@return The contents of the biblical section in RAW JSON format
	 * 		@throws UnsupportedEncodingException 
	 * @throws MalformedURLException */
	public static String fetchChapter (BibleID translation, BookID book, int chapter) throws UnsupportedEncodingException, MalformedURLException, IOException, ProtocolException
	{
		// Builds the URL
		StringBuilder link = new StringBuilder (kAPIURL);
		link.append("/bibles/");
		link.append(translation.getBibleKey());
		link.append("/passages/");
		link.append(book.getID());
		link.append('.');
		link.append(chapter);
		link.append("?");
		
		Map <String, String> parameters = new HashMap <String, String> ();
		parameters.put("content-type", "text");
		parameters.put("include-notes", "false");
		parameters.put("include-titles", "false");
		parameters.put("include-chapter-numbers", "false");
		parameters.put("include-verse-numbers", "true");
		parameters.put("include-verse-spans", "false");
		parameters.put("use-org-id", "false");

		link.append(getParamsString (parameters));
		
		URL url;

		url = new URL (link.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
			
		// Header
		con.setRequestProperty("api-key", kAPIKey);
		con.setDoOutput(true);
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		BufferedReader br = new BufferedReader (new InputStreamReader (con.getInputStream()));
		StringBuilder output = new StringBuilder ();
		String it;
		while ((it = br.readLine()) != null)
		{
			output.append((it));
		}
		con.disconnect();
	        
		return output.toString();
	}
	
	public static String generateJSONParams (Map <String, String> params) throws UnsupportedEncodingException
	{
		StringBuilder result = new StringBuilder ();
		result.append("{");
		
		int count = 0;
		for (Map.Entry<String, String> entry : params.entrySet())
		{
			// Key "...":
			result.append("\"");
			result.append(URLEncoder.encode (entry.getKey(), "UTF-8"));
			result.append("\"");
			// Value :"..."
			result.append(":\"");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("\"");
			++count;
			
			if (count < params.size())
			{
				result.append(",");
			}
		}
		result.append("}");
		
		return result.toString();
	}
	
	public static String getParamsString(Map<String, String> params) 
		throws UnsupportedEncodingException {
			StringBuilder result = new StringBuilder();
		 
			for (Map.Entry<String, String> entry : params.entrySet()) {
				result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				result.append("&");
			}
			
			String resultString = result.toString();
			return resultString.length() > 0
				? resultString.substring(0, resultString.length() - 1)
				: resultString;
	}
}
