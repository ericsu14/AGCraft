package com.joojet.plugins.mobs.monsters;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.metadata.FactionMetadata;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.util.ConvertColors;

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
	/** If true, a lightning bolt is summoned upon spawning the monster */
	protected boolean spawnLightning;
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
	/** Determines if the monster should automatically hunt a random nearby player upon spawning */
	protected boolean huntOnSpawn;
	/** Max radius in which the enemy will hunt their pray of huntOnSpawn is true */
	protected int huntRadius;
	/** Base attack damage of this mob */
	protected double attackDamage;
	/** Identifier for this custom mob type */
	protected MonsterType mobType;
	/** A list of factions this monster is apart of */
	protected HashSet <Faction> factions;
	/** A list of factions that this monster is set to target.
	 *  If this set has at least one value inserted, the monster only attack
	 *  entities that are either in this list and the monster's hit list or monsters
	 *  that are in its hit list but does not carry a faction tag. */
	protected HashSet <Faction> rivalFactions;
	/** A list of entities this monster should hunt either in addition not including
	 *  the entities the monster naturally hunts in vanilla MineCraft. This behavior can
	 *  be controlled by the variable, huntFromCustomListOnly. */
	protected ArrayList <EntityType> hitlist;
	/** A list of entities that this monster should ignore, meaning that they will never
	 *  become hostile to that entity. */
	protected HashSet <EntityType> ignoreList;
	
	public MobEquipment (MonsterType mobType)
	{
		this.mobType = mobType;
		this.name = "";
		this.color = ChatColor.WHITE;
		// -1 represents default health
		this.health = -1.0;
		this.onFire = false;
		this.showName = false;
		this.spawnLightning = false;
		this.effects = new ArrayList <PotionEffect> ();
		// Set up default drop rates
		this.dropRates = new float[6];
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.01f, 0.05f);
		// Words per line defaults to 6
		this.wordsPerLine = 5;
		this.biomes = new HashSet <Biome> ();
		// Spawn weight set to default
		this.spawnWeight = 1;
		this.huntOnSpawn = false;
		this.huntRadius = 25;
		// Use default (unmodified) attack damage if set to -1.0
		this.attackDamage = -1.0;
		// Factions
		this.factions = new HashSet <Faction> ();
		this.rivalFactions = new HashSet <Faction> ();
		// Hitlist
		this.hitlist = new ArrayList <EntityType> ();
		// Ignore List
		this.ignoreList = new HashSet <EntityType> ();
		// Adds the mob-equipment into the custom monster search trie
		AmplifiedMobSpawner.mobTable.insertWord(this.toString(), this);
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
	
	/** Returns the monster's helmet */
	public ItemStack getHelmet ()
	{
		return this.helmet;
	}
	
	/** Returns the monster's chestplate */
	public ItemStack getChestplate ()
	{
		return this.chestplate;
	}
	
	/** Returns the monster's leggings */
	public ItemStack getLeggings ()
	{
		return this.leggings;
	}
	
	/** Returns the monster's boots */
	public ItemStack getBoots ()
	{
		return this.boots;
	}
	
	/** Returns the monster's weapon */
	public ItemStack getWeapon ()
	{
		return this.weapon;
	}
	
	/** Returns the monster's offhand item */
	public ItemStack getOffhand ()
	{
		return this.offhand;
	}
	
	/** Returns the monster's name */
	public String getName ()
	{
		return this.name;
	}
	
	/** Returns the monster's ChatColor used to color their nametag */
	public ChatColor getChatColor ()
	{
		return this.color;
	}
	
	/** Returns the monster's custom health points */
	public double getHealth ()
	{
		return this.health;
	}
	
	/** Returns the monster's active potion effects as an ArrayList */
	public ArrayList <PotionEffect> getEffects ()
	{
		return this.effects;
	}
	
	/** Determines if the monster spawns with a permanent fire effect */
	public boolean onFire ()
	{
		return this.onFire;
	}
	
	/** Determines if the monster's nametag should be permanently visible to all players. */
	public boolean showName ()
	{
		return this.showName;
	}
	
	/** Determines if the monster should spawn with an active lightning bolt struck on its location. */
	public boolean spawnLightning ()
	{
		return this.spawnLightning;
	}
	
	/** Returns the monster's custom base attack damage */
	public double getBaseAttackDamage ()
	{
		return this.attackDamage;
	}
	
	/** True if the mob is set to hunt upon spawning */
	public boolean huntOnSpawn()
	{
		return this.huntOnSpawn;
	}
	
	/** Returns the monster's hunt on spawn radius */
	public int getHuntOnSpawnRaduis ()
	{
		return this.huntRadius;
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
	public void addPotionEffect (CustomPotionEffect... effects)
	{
		for (CustomPotionEffect effect : effects)
		{
			this.effects.add(effect.getPotionEffect());
		}
	}
	
	/** Adds a list of factions that this monster can be apart of */
	public void addFactions (Faction... factionParams)
	{
		for (Faction faction : factionParams)
		{
			this.factions.add(faction);
		}
	}
	
	/** Adds a list of rivaling factions this entity should hunt down */
	public void addRivalFactions (Faction... targets)
	{
		for (Faction faction : targets)
		{
			this.rivalFactions.add(faction);
		}
	}
	
	/** Adds a list of targets for this entity to hunt down */
	public void addTargetsToHitList (EntityType... targets)
	{
		for (EntityType entity : targets)
		{
			this.hitlist.add(entity);
		}
	}
	
	/** Adds a list of entities this monster should ignore */
	public void addEntitiesToIgnoreList (EntityType... entities)
	{
		for (EntityType entity : entities)
		{
			this.ignoreList.add(entity);
		}
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
	
	/** Returns the monster's chat color as a color enum type */
	public Color getColor ()
	{
		return ConvertColors.convertChatColorToColor(this.color);
	}
	
	/** Returns the monster's chat color as a dye color enum type */
	public DyeColor getDyeColor ()
	{
		return ConvertColors.convertDyeColorToColor(this.color);
	}
	
	/** Return the equipment's monster type identifier */
	public MonsterType getMonsterType ()
	{
		return this.mobType;
	}
	
	/** Returns the list of factions this monster is apart of */
	public HashSet <Faction> getFactions ()
	{
		return this.factions;
	}
	
	/** Returns the list of factions this entity should hunt */
	public HashSet <Faction> getRivalFactions ()
	{
		return this.rivalFactions;
	}
	
	/** Returns the list of  entities this monster should hunt */
	public ArrayList <EntityType> getHitList ()
	{
		return this.hitlist;
	}
	
	/** Returns the hashset of entities this monster should ignore */
	public HashSet <EntityType> getIgnoreList ()
	{
		return this.ignoreList;
	}
	
	/** Generates monster type metadata based on the mob equipment's properties*/
	public MonsterTypeMetadata generateMobTypeMetadata ()
	{
		return new MonsterTypeMetadata (this.mobType);
	}
	
	/** Generates a list of faction metadata types based on the mob equipment's properties */
	public ArrayList <FactionMetadata> generateFactionMetadata ()
	{
		ArrayList <FactionMetadata> result = new ArrayList <FactionMetadata> ();
		for (Faction faction : this.factions)
		{
			result.add(new FactionMetadata (faction));
		}
		return result;
	}
	
	/** Return the equipment's monster type identifier as a string. */
	@Override
	public String toString ()
	{
		return this.mobType.toString();
	}
}
