package com.joojet.biblefetcher.api;

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

import com.joojet.biblefetcher.constants.*;

public class APIFetcher 
{
	// API Bible
	private static final String kAPIURL = "https://api.scripture.api.bible/v1";
	
	// ESV API
	private static final String kESVAPIURL = "https://api.esv.org/v3";
	
	public static String fetchChapter (BibleID translation, BookID book, int chapter) throws IOException
	{
		if (translation.equals(BibleID.ESV))
		{
			return fetchChapterESV (translation, book, chapter);
		}
		return fetchChapterCommon (translation, book, chapter);
	}
	
	/** Uses call /v3/passage/text to retrieve an entire chapter from the bible.
	 *  	@param translation - The version of the bible we are using
	 *  	@param book - The book of the bible we are reading at
	 *  	@param chapter - The chapter of the book we are reading at
	 *  	@return The contents of the biblical section in RAW JSON format
	 * 		@throws UnsupportedEncodingException 
	 * 		@throws MalformedURLException */
	public static String fetchChapterESV (BibleID translation, BookID book, int chapter) throws IOException
	{
		StringBuilder link = new StringBuilder (kESVAPIURL);
		link.append("/passage/text/?");
		// Build parameters
		Map <String, String> parameters = new HashMap <String, String> ();
		StringBuilder query = new StringBuilder (book.getFormattedTitle());
		if (!book.isSingleChapter())
		{
			query.append(" ").append (chapter);
		}
		
		parameters.put("q", query.toString());
		parameters.put("include-passage-references", "false");
		parameters.put("include-first-verse-numbers", "false");
		parameters.put("include-footnotes", "false");
		parameters.put("include-headings", "false");
		parameters.put("include-passage-horizontal-lines", "false");
		parameters.put("include-heading-horizontal-lines", "false");
		parameters.put("indent-paragraphs", "0");
		parameters.put("indent-poetry", "false");
		
		link.append(getParamsString(parameters));
		// Opens connection
		URL url = new URL (link.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		// Header
		con.setRequestProperty("Authorization", "Token " + APIKeyReader.getAPIKey(APIName.ESV));
		con.setDoOutput(true);
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		// Get output
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
	
	/** Uses call /v1/bibles/{bibleId}/passages/{passageId} to retrieve an entire chapter from the bible.
	 *  	@param translation - The version of the bible we are using
	 *  	@param book - The book of the bible we are reading at
	 *  	@param chapter - The chapter of the book we are reading at
	 *  	@return The contents of the biblical section in RAW JSON format
	 * 		@throws UnsupportedEncodingException 
	 * @throws MalformedURLException */
	public static String fetchChapterCommon (BibleID translation, BookID book, int chapter) throws UnsupportedEncodingException, MalformedURLException, IOException, ProtocolException
	{
		// Builds the URL
		StringBuilder link = new StringBuilder (kAPIURL);
		link.append("/bibles/");
		link.append(translation.getBibleKey());
		link.append("/passages/");
		link.append(book.getID());
		
		if (!book.isSingleChapter())
		{
			link.append('.');
			link.append(chapter);
		}
		
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
		
		URL url = new URL (link.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
			
		// Header
		con.setRequestProperty("api-key", APIKeyReader.getAPIKey(APIName.API_BIBLE));
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
