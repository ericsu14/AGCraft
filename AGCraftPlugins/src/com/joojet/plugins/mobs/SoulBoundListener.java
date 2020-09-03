package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.metadata.EquipmentTypeMetadata;
import com.joojet.plugins.mobs.metadata.SoulBoundMetadata;
import com.joojet.plugins.mobs.util.serializer.SoulboundItemSerializer;

public class SoulBoundListener implements Listener 
{
	/** Caches any soulbounded items found in player death events */
	protected HashMap <UUID, List <ItemStack>> droppedItems;
	/** Used to serialize / deserialized the dropped items hashmap into a file
	 *  in the case of any server shutdowns */
	protected SoulboundItemSerializer serializer;
	
	public SoulBoundListener ()
	{
		this.droppedItems = new HashMap <UUID, List <ItemStack>> ();
		this.serializer = new SoulboundItemSerializer ();
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
		this.retrieveSerializedItems();
	}
	
	public void onDisable ()
	{
		HashMap <UUID, List <EquipmentTypes>> serializedData = new HashMap <UUID, List <EquipmentTypes>> ();
		List <ItemStack> currDrops = null;
		for (Entry<UUID, List<ItemStack>> entry : this.droppedItems.entrySet())
		{
			currDrops = entry.getValue();
			ArrayList <EquipmentTypes> equipmentTypes = new ArrayList <EquipmentTypes> ();
			for (ItemStack item : currDrops)
			{
				String equipmentID = new EquipmentTypeMetadata ().getStringMetadata(item.getItemMeta());
				Equipment equipment = AGCraftPlugin.equipmentInterpreter.searchTrie(equipmentID);
				if (equipment != null)
				{
					equipmentTypes.add(equipment.getEquipmentType());
				}
			}
			serializedData.put(entry.getKey(), equipmentTypes);
		}
		serializer.serializeFile(serializedData);
	}
	
	/** Scans a dead player's drops for any soulbounded items.
	 *  If a soulbounded item is found, it will automatically be removed from the player drops
	 *  and added back into their inventory. */
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
		this.droppedItems.put(event.getEntity().getUniqueId(), soulBoundedItems);
	}
	
	/** If the revived player has any soulbounded items cached onto memory, add them back into the inventory */
	@EventHandler
	public void onPlayerRespawn (PlayerRespawnEvent event)
	{
		Player p = event.getPlayer();
		
		UUID playerUUID = p.getUniqueId();
		if (!this.droppedItems.containsKey(playerUUID))
		{
			return;
		}
		
		PlayerInventory playerInventory = p.getInventory();
		List <ItemStack> soulBoundedItems = this.droppedItems.get(playerUUID);
		
		for (ItemStack drop : soulBoundedItems)
		{
			if (playerInventory.firstEmpty() != -1)
			{
				playerInventory.addItem(drop);
			}
		}
		
		this.droppedItems.remove(playerUUID);
	}
	
	protected void retrieveSerializedItems ()
	{
		HashMap <UUID, List <EquipmentTypes>> serializedData = serializer.getSerializedObject();
		if (serializedData != null)
		{
			List <EquipmentTypes> list = null;
			for (Entry<UUID, List<EquipmentTypes>> entry : serializedData.entrySet())
			{
				ArrayList <ItemStack> items = new ArrayList <ItemStack> ();
				list = entry.getValue();
				for (EquipmentTypes item : list)
				{
					Equipment equipment = AGCraftPlugin.equipmentInterpreter.searchTrie(item.toString());
					if (equipment != null)
					{
						items.add(equipment);
					}
				}
				this.droppedItems.put(entry.getKey(), items);
			}
		}
	}
	
}
