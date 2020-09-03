package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.mobs.metadata.SoulBoundMetadata;

public class SoulBoundListener implements Listener 
{
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Scans a dead player's drops for any soulbounded items.
	 *  If a soulbounded item is found, it will automatically be removed from the player drops
	 *  and added back into their inventory. */
	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent event)
	{
		List <ItemStack> drops = event.getDrops();
		SoulBoundMetadata checker = new SoulBoundMetadata ();
		PlayerInventory playerInventory = event.getEntity().getInventory();
		
		ArrayList <ItemStack> soulBoundedItems = new ArrayList <ItemStack> ();
		
		for (ItemStack current : drops)
		{
			if (current != null && 
					current.hasItemMeta() &&
					checker.getStringMetadata(current.getItemMeta()) != null)
			{
				soulBoundedItems.add(current);
			}
		}
		
		for (ItemStack current : soulBoundedItems)
		{
			if (playerInventory.firstEmpty() != -1)
			{
				playerInventory.addItem(current);
				drops.remove(current);
			}
		}
	}
}
