package com.joojet.plugins.utility.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.utility.config.JunkItemConfig;
import com.joojet.plugins.utility.enums.JunkClassifier;
import com.joojet.plugins.utility.interpreter.JunkCommandInterpreter;


public class ClearJunk extends AGCommandExecutor
{
	/** Used to interpret junk classifier commands */
	private JunkCommandInterpreter commandInterpreter;
	/** Stores mapping between targeted material items and their junk classifier categories */
	private HashMap <Material, JunkClassifier> junkItems;
	/** Stores the config file instance used to load in junk items from file */
	private JunkItemConfig config;
	
	
	public ClearJunk ()
	{
		super (CommandType.CLEAR_JUNK);
		// Initializes command interpreter
		this.commandInterpreter = new JunkCommandInterpreter ();
	}
	
	/** Reloads the config file for the clearjunk command */
	public void reloadConfigFile ()
	{
		// Init config file instance and load mappings from file
		this.config = new JunkItemConfig ();
		try
		{
			this.junkItems = this.config.generateMapping();
		}
		catch (RuntimeException error)
		{
			error.printStackTrace();
			System.err.println (error.getMessage());
			System.err.println ("Please delete the config file and run /reloadconfigfile on the terminal");
		}
	}
	
	/** Removes junk items from the player's inventory
	 * 		Usage:
	 * 			/removeJunk - Command with no arguments defaults to removing common items
	 * 			/removeJunk <list of classifiers> - Removes items tied to specified classifiers. Listing "all" removes
	 *                                              everything listed in this source file. */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{
		int n = args.length;
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			HashSet <JunkClassifier> classifiers = new HashSet <JunkClassifier> ();
			// TODO: execute /removeJunk common if no arguments provided
			if (n == 0)
			{
				classifiers.add(JunkClassifier.COMMON);
			}
			
			for (String arg : args)
			{
				JunkClassifier param = this.commandInterpreter.searchTrie(arg);
				if (param != null)
				{
					classifiers.add(param);
				}
				else
				{
					p.sendMessage(ChatColor.RED + "" + arg + " is an invalid classification.");
					p.sendMessage(ChatColor.RED + "Valid classifiers: " + this.printClassifiersAsList());
					return false;
				}
			}
			
			int removedItems = this.removeJunk(p, classifiers);
			
			p.sendMessage(ChatColor.GOLD + "Removed " + ChatColor.AQUA +  removedItems + ChatColor.GOLD + " item(s) from your inventory.");
		}
		return true;
	}
	
	
	/** Removes junk content from the player's inventory based on the filter passed into this function
	 *  and returns the amount of items removed. */
	private int removeJunk (Player player, HashSet <JunkClassifier> filter)
	{
		int count = 0;
		// Gathers items based on the passed filter
		ArrayList <ItemStack> queuedItems = new ArrayList <ItemStack> ();
		for (ItemStack item : player.getInventory())
		{
			if (item != null)
			{
				JunkClassifier classifier = this.junkItems.get(item.getType());
				if (classifier != null 
						&& checkMobArmorLoot (item, classifier) 
						&& (filter.contains(classifier) || filter.contains(JunkClassifier.ALL)))
				{
					boolean addToQueue = true;
					// Special case for used scrolls
					if (classifier.equals(JunkClassifier.COMMON) && item.getType().equals(Material.PAPER))
					{
						addToQueue = SummoningScroll.isWitheredScroll(item);
					}
					if (addToQueue)
					{
						queuedItems.add (item);
						count += item.getAmount();
					}
				}
			}
		}
		
		// Removes the targeted items from the player's inventory
		for (ItemStack item : queuedItems)
		{
			player.getInventory().remove(item);
		}
		
		return count;
	}
	
	/** Returns all classifiers as a list in String format */
	public String printClassifiersAsList ()
	{
		StringBuilder str = new StringBuilder ();
		for (JunkClassifier ele : JunkClassifier.values())
		{
			str.append(ele.name() + " | ");
		}
		return str.toString().substring(0, str.length() - 2);
	}
	
	/** Checks if an item is of type ARMOR or WEAPON and returns true if:
	 * 		- the item is unenchanted
	 * 		- The item has durability loss.
	 *  If the classifier is marked as anything else, it will always return true.
	 *  These are typically mob loot that nobody uses. */
	private boolean checkMobArmorLoot (ItemStack item, JunkClassifier classifier)
	{
		if (classifier.equals(JunkClassifier.ARMOR) || classifier.equals (JunkClassifier.WEAPONS))
		{
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta instanceof Damageable)
			{
				Damageable dmg = (Damageable) itemMeta;
				return (dmg.hasDamage() && !itemMeta.hasEnchants());
			}
			return false;
		}
		return true;
	}
	
}
