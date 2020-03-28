package com.joojet.plugins.biblefetcher.commands;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.joojet.biblefetcher.constants.*;
import com.joojet.biblefetcher.fetcher.BibleFetcher;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.biblefetcher.string.ContentParser;

import net.md_5.bungee.api.ChatColor;

public class Bible implements CommandExecutor 
{
	private BibleID bibleID;
	private BookID bookID;
	private int chapter;
	private int start;
	private int end;
	private int n;
	private int verseSize;
	
	public Bible ()
	{
		this.bookID = BookID.GEN;
		this.bibleID = BibleID.KJV;
		this.chapter = 1;
		this.start = 1;
		this.end = 1;
		this.n = 3;
		this.verseSize = 0;
	}
	
	/** Represents the /bible command.
	 * 	 Usage: /bible <translation> <book> <chapter> <start> <end>*/
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		try
		{
			ArrayList <String> verses = fetchVerses (args);
			verses.add(0, this.generateHeader());
			
			/* Parses the command */
			if (verses != null && sender instanceof Player)
			{
				Player player = (Player) sender;
				
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
				sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + this.generateHeader() + " has been added into your inventory.");
				return true;
			}
		}
		catch (IOException e) 
		{
			sender.sendMessage (ChatColor.RED + "[God] That chapter of the bible does not exist.");
		}
		
		catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "[God] " + e.getMessage());
		}

		return false;
	}
	
	/** Connects with the BibleParser API to fetch Bible passages based on what is present in the command's arguments
	 * 		@param args - Command arguments
	 * 		@return An ArrayList containing the fetched Bible passages
	 * 		@throws RuntimeException if there is a problem parsing the commandline arguments
	 * 		@throws IOException if there is a problem regarding connecting with the web API
	 * 		@throws MalformedURLException if there is a problem with the web API's URL
	 * 		@throws NumberFormatException if there is a problem extracting an integer based parameter in the commandline arguments */
	public ArrayList <String> fetchVerses (String [] args) throws RuntimeException, NumberFormatException, MalformedURLException, ProtocolException, IOException
	{
		this.n = args.length;
		ArrayList <String> result = null;
		if (this.n < 3)
		{
			throw new RuntimeException ("Insuffient parameters.\nUsage: /bible <translation> <book> <chapter> <start> <end>");
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
	private String generateHeader ()
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
	private String getSupportedBibles ()
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
