package com.joojet.plugins.biblefetcher.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.ItemStack;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class ClearBibles extends AGCommandExecutor
{
	
	public ClearBibles ()
	{
		super (CommandType.CLEAR_BIBLES);
	}
	
	/** Clears all generated Bibles from the player's inventory */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			ArrayList <ItemStack> removeList = new ArrayList <ItemStack> ();
			player.getInventory().forEach(item -> {
				// Verify if this book is a written book
				if (item != null && item.getType().equals(Material.WRITTEN_BOOK))
				{
					BookMeta meta = (BookMeta) item.getItemMeta();
					
					if (checkAuthor (meta.getAuthor()))
					{
						removeList.add(item);
					}
				}
			});
			
			int count = removeList.size();
			for (ItemStack item : removeList)
			{
				player.getInventory().remove(item);
			}
			
			player.sendMessage(ChatColor.GOLD + "[God] The Lord Gave, and the Lord Hath Taken Away " + ChatColor.AQUA + count + ChatColor.GOLD + " Bibles.");
			return true;
		}
		return false;
	}
	
	/** Returns true if the book's author matches one of the BibleIDs supported in this plugin */
	private boolean checkAuthor (String author)
	{
		return AGCraftPlugin.bibleInterpreter.searchBibleTrie(author) != null;
	}

}
