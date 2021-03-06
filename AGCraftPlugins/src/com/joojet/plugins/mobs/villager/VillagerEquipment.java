package com.joojet.plugins.mobs.villager;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;

public abstract class VillagerEquipment extends AlliedMob
{
	protected ArrayList <MerchantRecipe> recipes;
	
	/** Creates a new villager equipment object, which can be used to
	 *  set custom trades for our custom villagers.
	 *  @param type - Monster type identifier */
	public VillagerEquipment (MonsterType type)
	{
		super(type);
		this.addMobFlags(MobFlag.MAKE_SILENT);
		this.recipes = new ArrayList <MerchantRecipe> ();
	}
	
	public ArrayList <MerchantRecipe> getRecipes ()
	{
		return this.recipes;
	}
	
	/** Adds a new recipe into this merchant.
	 * 	@param item - Item to be sold
	 * 	@param trade - Material of the item needed to be traded in
	 * 	@param cost - Cost of the item (max is 64)
	 * 	@param stock - Number of items that can be sold before merchant runs out of stock */
	public void addRecipe (ItemStack item, Material trade, int cost, int stock)
	{
		// Fixes merchant trade cost to 64
		if (cost > 64)
		{
			cost = 64;
		}
		
		MerchantRecipe recipe = new MerchantRecipe (item, 0, stock, true);
		ItemStack tradeItem = new ItemStack (trade, cost);
		recipe.addIngredient(tradeItem);
		this.recipes.add(recipe);
	}
	
	public void addRecipe (MerchantRecipe recipe)
	{
		this.recipes.add(recipe);
	}
}
