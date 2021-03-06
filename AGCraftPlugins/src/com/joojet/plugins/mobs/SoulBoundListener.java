package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.mobs.metadata.SoulBoundMetadata;
import com.joojet.plugins.mobs.util.serializer.SoulboundItemSerializer;

public class SoulBoundListener implements AGListener, Listener
{
	/** Used to serialize / deserialized the dropped items hashmap into a file
	 *  in the case of any server shutdowns */
	protected SoulboundItemSerializer serializer;
	
	public SoulBoundListener ()
	{
		this.serializer = new SoulboundItemSerializer ();
	}
	
	@Override
	public void onEnable ()
	{
		this.serializer.recoverSerializedItems();
	}
	
	@Override
	public void onDisable ()
	{
		this.serializer.serialize();
	}
	
	/** Scans a dead player's drops for any soulbounded items.
	 *  If a soulbounded item is found, it will automatically be removed from the player drops
	 *  and added back into their inventory upon player respawn. */
	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent event)
	{
		List <ItemStack> drops = event.getDrops();
		SoulBoundMetadata checker = new SoulBoundMetadata ();
		
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
			drops.remove(current);
		}
		
		// Adds the player's soulbounded items into a temporary cache
		// so it can be loaded back into the player's inventory upon respawning
		this.serializer.addSoulboundedItems(event.getEntity().getUniqueId(), soulBoundedItems);
	}
	
	/** If the revived player has any soulbounded items cached onto the data structure
	 *  add them back into the inventory */
	@EventHandler
	public void onPlayerRespawn (PlayerRespawnEvent event)
	{
		Player p = event.getPlayer();
		
		UUID playerUUID = p.getUniqueId();
		
		PlayerInventory playerInventory = p.getInventory();
		List <ItemStack> soulBoundedItems = this.serializer.recoverSoulboundedItems(playerUUID);
		
		if (soulBoundedItems != null)
		{
			for (ItemStack drop : soulBoundedItems)
			{
				if (playerInventory.firstEmpty() != -1)
				{
					playerInventory.addItem(drop);
				}
			}
		}
	}
}
