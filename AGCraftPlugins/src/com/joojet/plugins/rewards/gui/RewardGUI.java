package com.joojet.plugins.rewards.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RewardGUI implements Listener 
{
    private Inventory inv;

    public RewardGUI() 
    {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        this.inv = Bukkit.createInventory(null, 18, "Rewards");

        // Put the items into the inventory
        initializeItems();
    }

    /** Loads in inventory items */
    public void initializeItems() 
    {

    }


    /** Opens the inventory */
    public void openInventory(final HumanEntity ent) 
    {
        this.inv = Bukkit.createInventory(null, 18, "Rewards");
        this.initializeItems();
    	ent.openInventory(inv);
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