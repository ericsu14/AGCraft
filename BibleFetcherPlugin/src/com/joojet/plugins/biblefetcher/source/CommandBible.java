package com.joojet.plugins.biblefetcher.source;

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

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;

public class CommandBible implements CommandExecutor 
{
	private BibleID bibleID;
	private BookID bookID;
	private int chapter;
	private int start;
	private int end;
	private int n;
	private int verseSize;
	
	public CommandBible ()
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
				
				ItemStack bible = new ItemStack (Material.WRITTEN_BOOK);
				BookMeta bibleContent = (BookMeta) bible.getItemMeta();
				
				bibleContent.setTitle(this.generateHeader());
				bibleContent.setAuthor(bibleID.getBibleID());
				bibleContent.setPages(verses);
				bible.setItemMeta(bibleContent);
				
				player.getInventory().addItem(bible);
			}
		}
		catch (Exception e)
		{
			System.out.println (e.getMessage());
			sender.sendMessage("[God] Error: " + e.getMessage());
		}

		return false;
	}
	
	public ArrayList <String> fetchVerses (String [] args) throws RuntimeException, NumberFormatException, MalformedURLException, ProtocolException, IOException
	{
		this.n = args.length;
		ArrayList <String> result = null;
		if (this.n < 3)
		{
			throw new RuntimeException ("Insuffient parameters");
		}
		
		this.bibleID = BibleFetcherPlugin.interpreter.searchBibleTrie(args[0]);
		this.bookID = BibleFetcherPlugin.interpreter.searchBookTrie(args[1]);
		this.chapter = Integer.parseInt(args[2]);
		
		if (this.bibleID == null)
		{
			throw new RuntimeException ("Unsupported bible translation. Supported translations are " + this.getSupportedBibles());
		}
		
		if (this.bookID == null)
		{
			throw new RuntimeException ("Unrecognized book chapter. Please try again.");
		}
		
		if (this.n == 3)
		{
			result = BibleFetcher.getVerses(bibleID, bookID, chapter);
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
			}
		}
		
		if (result != null)
		{
			this.verseSize = result.size();
		}
		return result;
	}
	
	public String generateHeader ()
	{
		StringBuilder result = new StringBuilder ();
		result.append(this.bookID.getFormattedTitle());
		result.append(" ");
		result.append(this.chapter);
		if (n >= 4)
		{	
			result.append(":");
			result.append(this.start);
			if (this.start < this.verseSize)
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
