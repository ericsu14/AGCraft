package com.joojet.plugins.utility.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.utility.enums.JunkClassifier;
import com.joojet.plugins.utility.interpreter.JunkCommandInterpreter;

import net.md_5.bungee.api.ChatColor;

public class RemoveJunk implements CommandExecutor
{
	private JunkCommandInterpreter commandInterpreter;
	private Hashtable <Material, JunkClassifier> junkItems;
	
	public RemoveJunk ()
	{
		this.junkItems = new Hashtable <Material, JunkClassifier> ();
		
		// Common
		this.junkItems.put(Material.ROTTEN_FLESH, JunkClassifier.COMMON);
		this.junkItems.put(Material.BONE, JunkClassifier.COMMON);
		this.junkItems.put(Material.STRING, JunkClassifier.COMMON);
		this.junkItems.put(Material.WHEAT_SEEDS, JunkClassifier.COMMON);
		
		// Natural blocks
		this.junkItems.put(Material.DIRT, JunkClassifier.NATURAL);
		this.junkItems.put(Material.SAND, JunkClassifier.NATURAL);
		this.junkItems.put(Material.GRAVEL, JunkClassifier.NATURAL);
		
		// Stones
		this.junkItems.put(Material.COBBLESTONE, JunkClassifier.STONE);
		this.junkItems.put(Material.ANDESITE, JunkClassifier.STONE);
		this.junkItems.put(Material.GRANITE, JunkClassifier.STONE);
		this.junkItems.put(Material.DIORITE, JunkClassifier.STONE);
		this.junkItems.put(Material.STONE, JunkClassifier.STONE);
		
		// Mob armor
		this.junkItems.put(Material.GOLDEN_BOOTS, JunkClassifier.ARMOR);
		this.junkItems.put(Material.GOLDEN_CHESTPLATE, JunkClassifier.ARMOR);
		this.junkItems.put(Material.GOLDEN_HELMET, JunkClassifier.ARMOR);
		this.junkItems.put(Material.GOLDEN_LEGGINGS, JunkClassifier.ARMOR);
		this.junkItems.put(Material.CHAINMAIL_BOOTS, JunkClassifier.ARMOR);
		this.junkItems.put(Material.CHAINMAIL_CHESTPLATE, JunkClassifier.ARMOR);
		this.junkItems.put(Material.CHAINMAIL_LEGGINGS, JunkClassifier.ARMOR);
		this.junkItems.put(Material.CHAINMAIL_HELMET, JunkClassifier.ARMOR);
		
		// Brewing
		this.junkItems.put(Material.SPIDER_EYE, JunkClassifier.BREWING);
		
		// Initializes command interpreter
		this.commandInterpreter = new JunkCommandInterpreter ();
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
				JunkClassifier param = this.commandInterpreter.searchJunkClassifierTrie(arg);
				if (param != null)
				{
					classifiers.add(param);
				}
				else
				{
					p.sendMessage(ChatColor.RED + "" + param + " is an invalid classification.");
					p.sendMessage(ChatColor.RED + "Valid classifiers: " + this.printClassifiersAsList());
					return false;
				}
			}
			
			int removedItems = 0;
			for (JunkClassifier param : classifiers)
			{
				removedItems += this.removeJunk(p, param);
			}
			
			p.sendMessage(ChatColor.GRAY + "Removed " + removedItems + " item(s) from your inventory.");
		}
		return true;
	}
	
	
	/** Removes junk content from the player's inventory based on the filter passed into this function
	 *  and returns the amount of items removed. */
	private int removeJunk (Player player, JunkClassifier filter)
	{
		int count = 0;
		// Gathers items based on the passed filter
		ArrayList <ItemStack> queuedItems = new ArrayList <ItemStack> ();
		for (ItemStack item : player.getInventory())
		{
			if (item != null)
			{
				JunkClassifier classifier = this.junkItems.get(item.getType());
				if (classifier != null && (classifier.equals(filter) || filter.equals(JunkClassifier.ALL)))
				{
					queuedItems.add (item);
					count += item.getAmount();
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
	
}
