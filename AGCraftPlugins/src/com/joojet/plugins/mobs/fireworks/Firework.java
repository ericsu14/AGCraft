package com.joojet.plugins.mobs.fireworks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Firework 
{
	protected int wordsPerLine = 5;
	
	/** Generates a new firework. To be overridden by firework implementations
	 * 	@param amount - Number of fireworks distributed
	 * 	@param power - Total power of the firework */
	public abstract ItemStack generateFirework (int amount, int power);
	
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
		@SuppressWarnings("deprecation")
		List <String> itemLore = meta.getLore();
		
		if (itemLore == null)
		{
			itemLore = new ArrayList<String> ();
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
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(meta);
	}
}
