package com.joojet.plugins.biblefetcher.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.biblefetcher.string.ContentParser;

public class FetchBibleTask extends Thread
{
	/** Player receiving the bible */
	protected Player player;
	/** Command-line arguments passed into the command */
	protected String[] args;
	/** Stores the Bible's translation version */
	private BibleID bibleID;
	/** Stores the book of the bible */
	private BookID bookID;
	/** Chapter of the bible */
	private int chapter;
	/** Beginning verse of the passages */
	private int start;
	/** Ending verse of the passage */
	private int end;
	/** Number of arguments in the command */
	private int n;
	/** Number of verses in the fetched book */
	private int verseSize;
	
	/** Runs a new thread to call the BibleFetcher API to
	 *  fetch the requested chapter / set of verses from
	 *  our API.
	 * 	@param player - The player that is receiving the bible
	 *  @param args - Command's arguments*/
	public FetchBibleTask (Player player, String[] args)
	{
		this.player = player;
		this.args = args;
		this.bookID = BookID.GEN;
		this.bibleID = BibleID.KJV;
		this.chapter = 1;
		this.start = 1;
		this.end = 1;
		this.n = 3;
		this.verseSize = 0;
	}
	
	/** Runs a new instance of the BibleFetcher command on another thread, which calls the 
	 *  BibleFetcher API to fetch for the player's requested chapter and verses. All of the fetched
	 *  content will be formatted to fit into a written book that will be distributed to the player's inventory
	 *  in game if the calls are successful. */
	@Override
	public void run() 
	{
		try
		{
			ArrayList <String> verses = fetchVerses (this.args);
			verses.add(0, this.generateHeader());
			
			// Check if the player's inventory is full
			if (player.getInventory().firstEmpty() == -1)
			{
				throw new RuntimeException ("Your inventory is full. Please clear at least one spot in your inventory before trying again.");
			}
			
			// Merges the pages together
			String mergedPages = ContentParser.mergeContent(ContentParser.formatContent(verses, this.start));
			ItemStack bible = new ItemStack (Material.WRITTEN_BOOK);
			BookMeta bibleContent = (BookMeta) bible.getItemMeta();
			
			bibleContent.setTitle(this.generateHeader());
			bibleContent.setAuthor(bibleID.getBibleID());
			bibleContent.setPages(ContentParser.formatContent (mergedPages, 1));
			bible.setItemMeta(bibleContent);
			
			player.getInventory().addItem(bible);
			player.sendMessage(ChatColor.AQUA + "" + this.generateHeader() + ChatColor.GOLD + " has been added into your inventory.");
		}
		catch (RuntimeException e)
		{
			System.err.println ("[BibleFetcher] " + e.getMessage());
			player.sendMessage (ChatColor.RED + "[God] " + e.getMessage());
		}
		
		catch (Exception e)
		{
			System.err.println ("[BibleFetcher] " + e.getMessage());
			player.sendMessage(ChatColor.RED + "[God] An internal error occured while fetching this passage.");
		}
	}
	
	/** Connects with the BibleParser API to fetch Bible passages based on what is present in the command's arguments
	 * 		@param args - Command arguments
	 * 		@return An ArrayList containing the fetched Bible passages
	 * 		@throws RuntimeException if there is a problem parsing the commandline arguments
	 * 		@throws IOException if there is a problem regarding connecting with the web API
	 * 		@throws MalformedURLException if there is a problem with the web API's URL
	 * 		@throws NumberFormatException if there is a problem extracting an integer based parameter in the commandline arguments */
	protected ArrayList <String> fetchVerses (String [] args) throws RuntimeException, NumberFormatException, MalformedURLException, ProtocolException, IOException
	{
		this.n = args.length;
		ArrayList <String> result = null;
		if (this.n < 3)
		{
			throw new RuntimeException ("Insufficient parameters.\nUsage: /bible <translation> <book> <chapter> <start> <end>");
		}
		
		this.bibleID = AGCraftPlugin.interpreter.searchBibleTrie(args[0]);
		this.bookID = AGCraftPlugin.interpreter.searchBookTrie(args[1]);
		this.chapter = Integer.parseInt(args[2]);
		
		if (this.bibleID == null)
		{
			throw new RuntimeException ("Unsupported bible translation. Supported translations are " + this.getSupportedBibles());
		}
		
		if (this.bookID == null)
		{
			throw new RuntimeException ("I do not ever recall writing the book of " + args[1] + ". Please try again.");
		}
		
		if (this.n == 3)
		{
			this.start = 1;
			result = BibleFetcher.getVerses(bibleID, bookID, chapter);
			this.end = result.size();
		}
		if (this.n >= 4)
		{
			this.start = Integer.parseInt(args[3]);
			if (this.n >= 5)
			{
				this.end = Integer.parseInt(args[4]);
				result = BibleFetcher.getVerses(bibleID, bookID, chapter, start, end);
			}
			else
			{
				result = BibleFetcher.getVerses(bibleID, bookID, chapter, start);
				this.end = result.size();
			}
		}
		
		if (result != null)
		{
			this.verseSize = result.size();
		}
		return result;
	}
	
	/** Generates a formatted header containing Book, chapter, and passage metadata. */
	protected String generateHeader ()
	{
		StringBuilder result = new StringBuilder ();
		result.append(this.bookID.getFormattedTitle());
		result.append(" ");
		result.append(this.chapter);
		if (n >= 4)
		{	
			result.append(":");
			result.append(this.start);
			if (this.start < this.verseSize || (this.start < (this.verseSize + this.start - 1)))
			{
				result.append("-");
				if (n == 4)
				{
					result.append((this.verseSize + this.start - 1));
				}
				else
				{
					result.append(this.end);
				}
			}
		}
		result.append(" (");
		result.append(bibleID.getBibleID());
		result.append(")");
		return result.toString();
	}
	
	/** Generates a String containing all of the supported bibles */
	protected String getSupportedBibles ()
	{
		StringBuilder result = new StringBuilder ();
		for (BibleID s : BibleID.values())
		{
			result.append(s.getBibleID());
			result.append(" | ");
		}
		return result.toString();
	}
}
