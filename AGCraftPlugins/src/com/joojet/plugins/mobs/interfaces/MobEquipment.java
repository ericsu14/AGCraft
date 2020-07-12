package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;

public abstract class MobEquipment 
{
	/** Name of the entity */
	protected String name;
	/** Color used to recolor the entity's display names */
	protected ChatColor color;
	/** Entity's health. A value of -1 uses the entity's default health */
	protected double health;
	/** The entity's helmet */
	protected ItemStack helmet;
	/** The entity's chestplate */
	protected ItemStack chestplate;
	/** The entity's leggings */
	protected ItemStack leggings;
	/** The entity's boots */
	protected ItemStack boots;
	/** The entity's weapon */
	protected ItemStack weapon;
	/** The entity's offhand item */
	protected ItemStack offhand;
	/** If set to true, the entity will spawn with a permanent burning effect */
	protected boolean onFire;
	/** If set to true, the entity will have its nametag visible to everyone */
	protected boolean showName;
	/** A list of potion effects applied on the entity upon spawning */
	protected ArrayList <PotionEffect> effects;
	/** URL base for custom player head skins */
	protected final String urlBase = "http://textures.minecraft.net/texture/";
	/** An array storing the drop chances for each item the entity has. */
	protected float dropRates[];
	/** Number of words that can fit in a single line for an item's lore */
	protected int wordsPerLine;
	/** Biomes this monster can spawn in. Putting the constant, THE_VOID allows the mob to be spawned in all biomes. */
	protected HashSet <Biome> biomes;
	/** The spawn weight for this monster. Higher weights equates to higher chances of the mob spawning */
	protected int spawnWeight;
	
	public MobEquipment ()
	{
		this.name = "";
		this.color = ChatColor.WHITE;
		// -1 represents default health
		this.health = -1.0;
		this.onFire = false;
		this.showName = false;
		this.effects = new ArrayList <PotionEffect> ();
		// Set up default drop rates
		this.dropRates = new float[6];
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.01f, 0.05f);
		// Words per line defaults to 6
		this.wordsPerLine = 5;
		this.biomes = new HashSet <Biome> ();
		// Spawn weight set to default
		this.spawnWeight = 1;
	}
	
	/** Sets up drop rates for this entity.
	 *  Rates are stored as doubles from ranges 0.0f-1.0f, where
	 *  1.0f indicates a 100% chance of dropping
	 * 		@param helmet - Drop rate for helmet
	 * 		@param chestplate - Drop rate for chestplate
	 * 		@param leggings - Drop rate for leggings
	 * 		@param boots - Drop rate for boots
	 * 		@param weapon - Drop rate for weapons
	 * 		@param offhand - Drop rate for offhand */
	public void setDropRates (float helmet, float chestplate, float leggings, float boots, float weapon, float offhand)
	{
		this.dropRates[0] = helmet;
		this.dropRates[1] = chestplate;
		this.dropRates[2] = leggings;
		this.dropRates[3] = boots;
		this.dropRates[4] = weapon;
		this.dropRates[5] = offhand;
	}
	
	/** Returns all of the mob's equipment in the form of an array */
	public ItemStack [] getEquipment ()
	{
		ItemStack itemList[] = new ItemStack [6];
		
		itemList[0] = this.helmet;
		itemList[1] = this.chestplate;
		itemList[2] = this.leggings;
		itemList[3] = this.boots;
		itemList[4] = this.weapon;
		itemList[5] = this.offhand;
		
		return itemList;
	}
	
	/** Return the drop rates for each item in the mob's inventory */
	public float[] getDropRates ()
	{
		return this.dropRates;
	}
	
	public ItemStack getHelmet ()
	{
		return this.helmet;
	}
	
	public ItemStack getChestplate ()
	{
		return this.chestplate;
	}
	
	public ItemStack getLeggings ()
	{
		return this.leggings;
	}
	
	public ItemStack getBoots ()
	{
		return this.boots;
	}
	
	public ItemStack getWeapon ()
	{
		return this.weapon;
	}
	
	public ItemStack getOffhand ()
	{
		return this.offhand;
	}
	
	public String getName ()
	{
		return this.name;
	}
	
	public ChatColor getChatColor ()
	{
		return this.color;
	}
	
	public double getHealth ()
	{
		return this.health;
	}
	
	public ArrayList <PotionEffect> getEffects ()
	{
		return this.effects;
	}
	
	public boolean onFire ()
	{
		return this.onFire;
	}
	
	public boolean showName ()
	{
		return this.showName;
	}
	
	/** Returns this monster's spawnweight */
	public int getSpawnWeight ()
	{
		return this.spawnWeight;
	}
	
	/** Sets this monster's spawn weight to a new value */
	public void setSpawnWeight (int spawnWeight)
	{
		this.spawnWeight = spawnWeight;
	}
	
	/** Adds biomes to the list of biomes the monster can spawn in */
	public void addBiomes (Biome... biomes)
	{
		for (Biome biome : biomes)
		{
			this.biomes.add(biome);
		}
	}
	
	/** Returns the biomes this monster can spawn in as an arraylist */
	public HashSet <Biome> getSpawnBiomes ()
	{
		return this.biomes;
	}
	
	/** Adds a custom potion effect to the monster */
	public void addPotionEffect (CustomPotionEffect effect)
	{
		this.effects.add(effect.getPotionEffect());
	}
	
	/** Americanizes a name by applying the USA colors to every character in a string
	 *  in an alternating pattern */
	public String americanizeText (String str)
	{
		StringBuilder result = new StringBuilder ();
		
		int pattern = 0;
		for (char c : str.toCharArray())
		{
			switch (pattern)
			{
				case 0:
					result.append(ChatColor.RED);
					break;
				case 1:
					result.append(ChatColor.WHITE);
					break;
				default:
					result.append(ChatColor.BLUE);
					break;
			}
			result.append(c);
			++pattern;
			
			if (pattern > 2)
			{
				pattern = 0;
			}
				
		}
		return result.toString();
	}
}
