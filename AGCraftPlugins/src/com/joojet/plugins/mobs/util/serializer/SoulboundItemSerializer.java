package com.joojet.plugins.mobs.util.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.EquipmentLoader;
import com.joojet.plugins.mobs.metadata.EquipmentTypeMetadata;
import com.joojet.plugins.mobs.metadata.SoulBoundMetadata;

public class SoulboundItemSerializer extends AbstractDataSerializer<HashMap <UUID, List <String>>>
{
	public static String soulboundFileName = "soulbounded-cache.ser";
	
	/** Stores a list of raw soulbounded items recovered from the player's inventory
	 *  upon death */
	protected HashMap <UUID, List <ItemStack>> droppedItems;
	
	/** Stores a list of custom equipment identifiers attached to the custom soulbounded items
	 *  that can be serialized into a file upon the event of a server crash / restart.
	 *  
	 *  This is to prevent permanent loss of soulbounded items, despite the fact that their
	 *  enchants will be completely reset.*/
	protected HashMap <UUID, List <String>> droppedItemsSerializable;
	
	/** A registry of all known custom equipment contained in this plugin */
	protected EquipmentLoader equipmentLoader;
	
	public SoulboundItemSerializer() 
	{
		super(soulboundFileName);
		
		this.droppedItems = new HashMap <UUID, List <ItemStack>> ();
		this.droppedItemsSerializable = new HashMap <UUID, List <String>>();
		this.equipmentLoader = new EquipmentLoader();
	}
	
	/** Registers a list of captured soulbounded item into the container
	 * 	@param uuid - UUID of the player these soulbounded items belongs to
	 *  @param item - The list of captured soulbounded items tied to the player */
	public void addSoulboundedItems (UUID uuid, List <ItemStack> items)
	{
		this.droppedItems.put(uuid, items);
		
		List <String> itemIdentifiers = new ArrayList <String> ();
		SoulBoundMetadata checker = new SoulBoundMetadata ();
		String identifier;
		
		for (ItemStack item : items)
		{
			if (item.hasItemMeta())
			{
				identifier = checker.getStringMetadata(item.getItemMeta());
				if (identifier != null)
				{
					itemIdentifiers.add(new EquipmentTypeMetadata().getStringMetadata(item.getItemMeta()));
				}
			}
		}
		
		this.droppedItemsSerializable.put(uuid, itemIdentifiers);
	}
	
	/** Recovers the list of stored soulbounded items from the container if an entry exists
	 *  for the passed UUID. This will also remove the itemstore from the container upon successful retrieval.
	 *  Returns null if the items do not exist.
	 *  @param uuid - UUID of the player in which the desired items belong to */
	public List <ItemStack> recoverSoulboundedItems (UUID uuid)
	{
		List <ItemStack> result = null;
		if (this.droppedItems.containsKey(uuid))
		{
			result = this.droppedItems.get(uuid);
			this.droppedItems.remove(uuid);
			this.droppedItemsSerializable.remove(uuid);
		}
		
		return result;
	}
	
	/** Serializes all unrecovered soulbounded items into a file */
	public void serialize ()
	{
		this.serializeFile(this.droppedItemsSerializable);
	}
	
	/** Recovers all serialized items from the data file */
	public void recoverSerializedItems ()
	{
		HashMap <UUID, List<String>> recoveredItems = this.getSerializedObject();
		if (recoveredItems == null)
		{
			return;
		}
		
		this.droppedItemsSerializable = recoveredItems;
		
		List <ItemStack> items = null;
		ItemStack current = null;
		for (Entry<UUID, List<String>> value : recoveredItems.entrySet())
		{
			items = new ArrayList <ItemStack> ();
			for (String itemID : value.getValue())
			{
				current = this.equipmentLoader.getInterpreter().searchTrie(itemID);
				if (current != null)
				{
					items.add(current);
				}
			}
			this.droppedItems.put(value.getKey(), items);
		}
		
		if (!this.droppedItems.isEmpty())
		{
			this.deleteSerializedFile();
		}
	}
}
