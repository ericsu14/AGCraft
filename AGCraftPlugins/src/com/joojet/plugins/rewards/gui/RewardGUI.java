package com.joojet.plugins.rewards.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.rewards.database.RewardDatabaseManager;
import com.joojet.plugins.rewards.interfaces.RewardEntry;

import net.md_5.bungee.api.ChatColor;

public class RewardGUI implements Listener 
{
    private Inventory inv;
    private ArrayList <RewardEntry> entries;
    private Player player;

    public RewardGUI(Player player) 
    {
        this.player = player;
        this.entries = new ArrayList <RewardEntry> ();
    }

    /** Opens the inventory */
    public void openInventory() 
    {
    	try 
    	{
			this.entries = RewardDatabaseManager.fetchUnclaimedRewards(player.getUniqueId());
	        this.inv = Bukkit.createInventory(null, entries.size() + 1, "Rewards");
	        for (RewardEntry entry : entries)
	        {
	        	// Adds event information to the item's lore
	        	ItemStack reward = entry.getReward().getReward();
	        	ItemMeta meta = reward.getItemMeta();
	        	List <String> lore = meta.getLore();
	        	lore.add(ChatColor.GOLD + entry.getEvent().getFormattedLore());
	        	meta.setLore(lore);
	        	reward.setItemMeta(meta);
	        	this.inv.addItem(reward);
	        }
	        player.openInventory(inv);
		} 
    	catch (SQLException e) 
    	{
    		player.sendMessage(ChatColor.RED + "An internal error occured while trying to fetch rewards.");
			System.out.println (e.getMessage());
		}
    }

    /** Handles inventory click events */
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) 
    {
        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's
        p.sendMessage("You clicked at slot " + e.getRawSlot());
    }

    /** Disables dragging inventory items */
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) 
    {
        if (e.getInventory() == inv) 
        {
          e.setCancelled(true);
        }
    }
}