package com.joojet.plugins.rewards.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.fireworks.tasks.SpawnFireworksOnLocationTask;
import com.joojet.plugins.rewards.database.RewardDatabaseManager;
import com.joojet.plugins.rewards.interfaces.RewardEntry;

import org.bukkit.ChatColor;

public class RewardGUI implements Listener 
{
	/** The inventory used for this rewards GUI */
    private Inventory inv;
    /** Stores a list of reward items shown to the player */
    private List <RewardEntry> entries;
    /** Stores a reference to the player opening the rewards GUI */
    private Player player;
    /** Amount of words that could be fitted per line when generating
     *  item lore for a rewards item */
    private int wordsPerLine;
    /** The total amount of reward items that will be displayed in the rewards menu at a time */
    private int maxInvSize = 54;

    public RewardGUI(Player player) 
    {
        this.player = player;
        this.entries = new ArrayList <RewardEntry> ();
        this.wordsPerLine = 6;
    }
    
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, AGCraftPlugin.plugin);
	}

    /** Opens the inventory */
    public void openInventory() 
    {
    	
    	new AsyncTask <List <RewardEntry>> ()
    	{

			@Override
			protected List<RewardEntry> getAsyncData() throws SQLException 
			{
				return RewardDatabaseManager.fetchUnclaimedRewards(player.getUniqueId());
			}

			@Override
			protected void handlePromise(List<RewardEntry> data) 
			{
				entries = data;
				inv = Bukkit.createInventory(null, maxInvSize, "Claim Rewards");
		        int index = 0;
		        for (RewardEntry entry : entries)
		        {
		        	// Prevents overflow
		        	if (index > maxInvSize)
		        	{
		        		break;
		        	}
		        	
		        	// Appends lore and reward ID to the item. Reward ID is always in the last slot of the item's lore
		        	ItemStack reward = entry.getReward().getReward();
		        	addLoreToItemMeta(reward, entry.getEvent().getFormattedLore(), ChatColor.YELLOW);
		        	addLoreToItemMeta(reward, entry.getRewardID() + "", ChatColor.MAGIC);
		        	inv.addItem(reward);
		        	++index;
		        }
		        player.openInventory(inv);
			}
    		
    	}.runAsyncTask();
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

        int slot = e.getRawSlot();
        
        if (slot >= 0 && slot <= maxInvSize - 1)
        {
            // Check if the player's inventory is full
    		if (player.getInventory().firstEmpty() == -1)
    		{
    			this.player.sendMessage (ChatColor.RED + "Your inventory is full. Please clear at least one spot in your inventory before trying again.");
    			this.player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
    			return;
    		}
    		
    		// If the player is trying to click on a cake and is currently in the nether, cancel that
    		if (player.getWorld().getEnvironment() == Environment.NETHER
    				&& clickedItem.getType() == Material.CAKE)
    		{
    			this.player.sendMessage(ChatColor.RED + "I am sorry " + ChatColor.GOLD + player.getDisplayName() + ChatColor.RED +
    					", but I cannot let you do that. Please go back to the " + ChatColor.GOLD + "overworld" + ChatColor.RED + " to claim this reward.");
    			this.player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
    			return;
    		}
    		
        	int rewardID = this.getRewardID(clickedItem);
        	inv.remove(clickedItem);
        	
        	new AsyncTask <Boolean> ()
        	{
				@Override
				protected Boolean getAsyncData() throws SQLException 
				{
					RewardDatabaseManager.claimReward(rewardID);
					return true;
				}

				@Override
				protected void handlePromise(Boolean data) 
				{
					removeIDField(clickedItem);
	        		player.getInventory().addItem(clickedItem);
	        		player.sendMessage (ChatColor.AQUA + "Acquired " + clickedItem.getItemMeta().getDisplayName() + ChatColor.AQUA +"!");
	        		player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
	        		// Checks if we should launch a small fireworks show based on the retrieved item
	        		if (clickedItem.getType() == Material.CAKE)
	        		{
	        			new SpawnFireworksOnLocationTask (player.getLocation(), 48, 3, 250).runTaskTimer(AGCraftPlugin.plugin, 30, 15);
	        			player.sendMessage(ChatColor.GOLD + "Yay! Happy birthday " + ChatColor.AQUA + player.getDisplayName() +
	        					ChatColor.GOLD + "!!");
	        			player.getLocation().getWorld().playSound(player.getLocation(), Sound.MUSIC_DISC_CAT, SoundCategory.RECORDS, 10.0f, 1.0f);
	        			player.getWorld().setTime(14000);
	        		}
				}
        		
        	}.runAsyncTask();
        }
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
    
    /** Unregisters listeners once the player closes rewards inventory */
    @EventHandler
    public void onInventoryClose (final InventoryCloseEvent e)
    {
    	HandlerList.unregisterAll(this);
    }
    
    /** Returns the ID of an item
     * 		@param ItemStack item - item we are extracting the reward entry ID from */
	public int getRewardID (ItemStack item)
    {
    	ItemMeta meta = item.getItemMeta();
    	List <String> lore = meta.getLore();
    	
    	if (lore == null)
    	{
    		return -1;
    	}
    	
    	int id = -1;
    	int n = lore.size();
    	
    	try
    	{
    		id = Integer.parseInt(lore.get(n - 1).substring(2));
    	}
    	catch (NumberFormatException e)
    	{
    		this.player.sendMessage (ChatColor.RED + "Unable to get reward due to an internal error");
    		System.err.println ("Player " + this.player.getDisplayName() + " is having trouble opening up " + item.toString());
    		e.printStackTrace();
    	}
    	return id;
    }
    
    /** Removes ID field from an item's lore
     * 		@param item - Item we are stripping the ID info out of */
	public void removeIDField (ItemStack item)
    {
    	ItemMeta meta = item.getItemMeta();
    	List <String> itemLore = meta.getLore();
    	if (itemLore == null)
    	{
    		return;
    	}
    	int n = itemLore.size();
    	itemLore.remove(n - 1);
    	
    	meta.setLore(itemLore);
    	item.setItemMeta(meta);
    }
    
	/** Adds a new lore string into the passed ItemMeta. The String will be split into multiple tokens depending on how many
	 *  words can fit in a single line.
	 * 		@param item - Item we are adding lore info into
	 * 		@param lore - The lore we are adding into the item meta
	 * 		@param color - Color of the lore text */
	public void addLoreToItemMeta (ItemStack item, String lore, ChatColor color)
    {
    	StringBuilder str = new StringBuilder();
		str.append(color);
		ItemMeta meta = item.getItemMeta();
		List <String> itemLore = meta.getLore();
		
		if (itemLore == null)
		{
			itemLore = new ArrayList<String> ();
		}
		
		// If display name does not exist, create one automatically for the item
		if (!meta.hasDisplayName())
		{
			String displayName = StringUtil.generateReadableName(item.getType().toString(), "_");
			meta.setDisplayName(ChatColor.AQUA + displayName);
		}
		
		String[] tokens = lore.split(" ");
		
		int count = 0;
		for (String token : tokens)
		{
			str.append(token);
			str.append(" ");
			++count;
			
			if (count >= this.wordsPerLine)
			{
				itemLore.add(str.toString().trim());
				str = new StringBuilder();
				count = 0;
				str.append(color);
			}
		}
		
		if (!str.toString().substring(2).isEmpty())
		{
			itemLore.add(str.toString().trim());
		}
		meta.setLore(itemLore);
		item.setItemMeta(meta);
	}
    
}