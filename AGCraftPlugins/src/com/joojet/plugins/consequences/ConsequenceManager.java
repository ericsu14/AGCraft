package com.joojet.plugins.consequences;

import java.util.Calendar;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.joojet.plugins.consequences.database.ConsequenceDatabaseManager;
import com.joojet.plugins.consequences.interpreter.CalendarFieldInterpreter;
import com.joojet.plugins.mobs.equipment.head.ClownHead;

public class ConsequenceManager implements Listener 
{
	public static CalendarFieldInterpreter interpreter = new CalendarFieldInterpreter();
	
	@EventHandler
	public void handleConsequenceLoginEvent (PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		
		UUID uuid = p.getUniqueId();
		
		// If the player has any active consequences, force him to wear a clown head
		if (ConsequenceDatabaseManager.hasConsequences(uuid))
		{
			this.forceClownHead(p, "BAD SPORT! Because of your recent actions on this server, you will be forced to wear a clown head for a certain period of time as consequence.");
		}
		// Otherwise, remove the clown head from the player if he is wearing it
		else
		{
			removeClownHead (p);
		}
	}
	
	/** If the player has an active consequence, force him to wear the hat again */
	@EventHandler
	public void handlePlayerDeathEvent (PlayerRespawnEvent event)
	{
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();
		
		// If the player has any active consequences, force him to wear a clown head
		if (ConsequenceDatabaseManager.hasConsequences(uuid))
		{
			this.forceClownHead(p, "You think you can get away that easily...");
		}
	}
	
	
	/** Forces the player to wear a clown head
	 * 		@param p - Player being forced to wear the head
	 * 		@param message - Custom message to be shown to the player */
	private void forceClownHead (Player p, String message)
	{
		Calendar longest = ConsequenceDatabaseManager.getLongestRunningConsequence(p.getUniqueId());
		
		PlayerInventory inventory = p.getInventory();
		// If the player has a helmet, force him to drop that helmet
		if (inventory.getHelmet() != null)
		{
			ItemStack helmet = inventory.getHelmet();
			// If the player is not already wearing a clown head, force him to wear one
			if (!ClownHead.isClownHead(helmet))
			{
				p.getWorld().dropItem(p.getLocation(), helmet);
			}
		}
		inventory.setHelmet(new ClownHead (ChatColor.RED, longest));
		p.sendMessage(ChatColor.DARK_RED + message);
		p.sendMessage(ChatColor.RED + "This will take effect until " + longest.getTime().toString());
	}
	
	/** Removes a clown head from the player's inventory if he is wearing one */
	private void removeClownHead (Player p)
	{
		PlayerInventory inventory = p.getInventory();
		
		if (inventory.getHelmet() != null)
		{
			ItemStack helmet = inventory.getHelmet();
			if (ClownHead.isClownHead(helmet))
			{
				inventory.setHelmet(new ItemStack (Material.LEATHER_HELMET, 1));
				p.sendMessage(ChatColor.GREEN +"Your consequences have been lifted!");
			}
		}
	}
}