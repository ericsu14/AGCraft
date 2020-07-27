package com.joojet.plugins.utility.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;

import org.bukkit.ChatColor;

public class AutoSmelt extends AGCommandExecutor
{
	// Number of coal / charcoal in the player's inventory
	private int numCoals;
	// Total number of coal blocks in the player's inventory
	private int numCoalBlocks;
	// Total number of iron ores in the player's inventory
	private int numIronOres;
	// Total number of gold ores in the player's inventory
	private int numGoldOres;
	
	public AutoSmelt ()
	{
		super (CommandType.AUTOSMELT);
		this.resetCountingVariables();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{
		this.resetCountingVariables();
		
		if (sender instanceof Player)
		{
			Player p = (Player) sender;

			this.updateCounts(p);
			
			// Cannot use this command if the player does not have any iron / gold ores in their inventory
			if (this.getTotalOreCount() == 0)
			{
				p.sendMessage(ChatColor.RED + "Operation failed. No ores detected in your inventory.");
				return false;
			}
			
			// Checks if the player has enough coals to complete this operation
			// Recall that each piece of coal can smelt 8 items.
			// Thus if the player has 64 coal, the player can smelt
			// 512 items. If the player has 612 items, then the player cannot use this command,
			// since 512 >= 612. Thus, the player would need 612 - 512 = 100 / 8 = 12.5 (round to ceil) = 13 coal
			// to complete this operation
			if ((this.getTotalCoalCount() * 8 < this.getTotalOreCount()))
			{
				p.sendMessage(ChatColor.RED + "Operation failed. You need " + this.getRemainingCoal() + " coal to complete this operation.");
				return false;
			}
			
			removeCoal (p);
			convertOres (p);
			p.sendMessage(ChatColor.GOLD + "Successfully converted " + ChatColor.AQUA + this.getTotalOreCount() + ChatColor.GOLD + " ores to their respective ignots.");
			return true;
		}
		return false;
	}
	
	/** Makes a calculation to get the exact amount of coal the player needs to smelt all of his items */
	private int getRemainingCoal ()
	{
		double remaining = ((double) this.getTotalOreCount() - (double) (this.getTotalCoalCount() * 8)) / 8;
		remaining = Math.ceil(remaining);
		return (int) remaining;
	}
	
	/** Returns the total amount of coal units the player currently has. Coal blocks are automatically converted to individual pieces
	 *  of coal. */
	private int getTotalCoalCount ()
	{
		return this.numCoals + (this.numCoalBlocks * 10);
	}
	
	/** Returns the total number of gold and iron ores the player currently has */
	private int getTotalOreCount ()
	{
		return this.numGoldOres + this.numIronOres;
	}
	
	/** Updates the number of coal, coal blocks, iron ores, and gold ores through scanning the player's inventory */
	private void updateCounts (Player p)
	{
		for (ItemStack item : p.getInventory())
		{
			if (item !=  null)
			{
				switch (item.getType())
				{
					case COAL:
						this.numCoals += item.getAmount();
						break;
					case CHARCOAL:
						this.numCoals += item.getAmount();
						break;
					case COAL_BLOCK:
						this.numCoalBlocks += item.getAmount();
						break;
					case IRON_ORE:
						this.numIronOres += item.getAmount();
						break;
					case GOLD_ORE:
						this.numGoldOres += item.getAmount();
						break;
					default:
						break;
				}
			}
		}
	}
	
	/** Removes coal / coal blocks / charcoal items from the player's inventory that are going to be smelted */
	private void removeCoal (Player p)
	{
		// Gets the number of coal / coal blocks to be removed from the player's inventory
		int totalCoalBlocks = this.numCoalBlocks;
		int coal = 0, coalBlocks = 0;
		int totalSmelts = this.getTotalOreCount();
		
		while (totalSmelts > 0)
		{
			if (totalCoalBlocks > 0)
			{
				totalSmelts -= 80;
				++coalBlocks;
				--totalCoalBlocks;
			}
			else
			{
				totalSmelts -= 8;
				++coal;
			}
		}
		
		ArrayList <ItemStack> queuedItems = new ArrayList <ItemStack> ();
		for (ItemStack item : p.getInventory())
		{
			if (item != null && (item.getType().equals(Material.COAL) || item.getType().equals(Material.CHARCOAL)
								|| item.getType().equals(Material.COAL_BLOCK)))
			{
				int amount = item.getAmount();
				
				switch (item.getType())
				{
					case COAL_BLOCK:
						if (coalBlocks > 0)
						{
							if ((item.getAmount() == 64 && coalBlocks - amount >= 0)
									|| amount - coalBlocks <= 0)
							{
								queuedItems.add(item);
							}
							else
							{
								item.setAmount(amount - coalBlocks);
							}
							coalBlocks -= amount;
						}
						break;
					// Either coal or charcoal at this point
					default:
						if (coal > 0)
						{
							if ((item.getAmount() == 64 && coal - amount >= 0)
									|| amount - coal <= 0)
							{
								queuedItems.add(item);
							}
							else
							{
								item.setAmount(amount - coal);
							}
							coal -= amount;
						}
						break;
				}
			}
		}
		
		for (ItemStack item : queuedItems)
		{
			p.getInventory().removeItem(item);
		}
	}
	
	/** Converts all gold and iron ores in the player's inventory to ignots */
	private void convertOres (Player p)
	{
		ArrayList <ItemStack> queuedItems = new ArrayList <ItemStack> ();
		for (ItemStack item : p.getInventory())
		{
			if (item != null)
			{
				switch (item.getType())
				{
					case IRON_ORE:
						queuedItems.add(item);
						break;
					case GOLD_ORE:
						queuedItems.add(item);
						break;
					default:
						break;
				}
			}
		}
		
		for (ItemStack item : queuedItems)
		{
			ItemStack newItem;
			
			switch (item.getType())
			{
				case GOLD_ORE:
					newItem = new ItemStack (Material.GOLD_INGOT, item.getAmount());
					break;
				default:
					newItem = new ItemStack (Material.IRON_INGOT, item.getAmount());
					break;
			}
			p.getInventory().remove(item);
			p.getInventory().addItem(newItem);
		}
	}
	
	/** Resets all counting variables */
	private void resetCountingVariables ()
	{
		this.numCoals = 0;
		this.numCoalBlocks = 0;
		this.numIronOres = 0;
		this.numGoldOres = 0;
	}

}
