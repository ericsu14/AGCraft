package com.joojet.plugins.mobs.monsters;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.offhand.TippedArrow;
import com.joojet.plugins.mobs.metadata.FactionMetadata;
import com.joojet.plugins.mobs.metadata.IgnorePlayerMetadata;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.util.ConvertColors;
import com.joojet.plugins.music.enums.MusicType;

public abstract class MobEquipment 
{
	/** Name of the entity */
	protected String name;
	/** Color used to recolor the entity's display names */
	protected ChatColor color;
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
	/** A list of potion effects applied on the entity upon spawning */
	protected ArrayList <PotionEffect> effects;
	/** URL base for custom player head skins */
	protected final String urlBase = "http://textures.minecraft.net/texture/";
	/** An array storing the drop chances for each item the entity has. */
	protected float dropRates[];
	/** Biomes this monster can spawn in. Putting the constant, THE_VOID allows the mob to be spawned in all biomes. */
	protected EnumSet <Biome> biomes;
	/** Contains the monster's custom stats */
	protected EnumMap <MonsterStat, Double> mobStats;
	/** Identifier for this custom mob type */
	protected MonsterType mobType;
	/** A list of factions this monster is apart of */
	protected EnumSet <Faction> factions;
	/** A list of factions that this monster is set to target.
	 *  If this set has at least one value inserted, the monster only attack
	 *  entities that are either in this list and the monster's hit list or monsters
	 *  that are in its hit list but does not carry a faction tag. */
	protected EnumSet <Faction> rivalFactions;
	/** A list of entities this monster should hunt either in addition not including
	 *  the entities the monster naturally hunts in vanilla MineCraft. This behavior can
	 *  be controlled by the variable, huntFromCustomListOnly. */
	protected ArrayList <EntityType> hitlist;
	/** A list of entities that this monster should ignore, meaning that they will never
	 *  become hostile to that entity. */
	protected EnumSet <EntityType> ignoreList;
	/** A set of flags that could be applied to the monster upon spawning */
	protected EnumSet <MobFlag> mobFlags;
	/** Contains any custom loot this custom monster can have */
	protected ArrayList <MonsterDrop> loot;
	/** Custom monster that this entity can ride upon spawning */
	protected MountedMob mount;
	/** Custom tipped arrow the monster is set to shoot with */
	protected TippedArrow tippedArrow;
	/** Boss theme that plays when any player engages this mob */
	protected MusicType bossTheme;
	
	public MobEquipment (MonsterType mobType)
	{
		this.mobType = mobType;
		this.name = "";
		this.color = ChatColor.WHITE;
		this.effects = new ArrayList <PotionEffect> ();
		// Set up default drop rates
		this.dropRates = new float[6];
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.01f, 0.05f);
		// Words per line defaults to 6
		this.biomes = EnumSet.noneOf(Biome.class);
		// Factions
		this.factions = EnumSet.noneOf(Faction.class);
		this.rivalFactions = EnumSet.noneOf(Faction.class);
		// Hitlist
		this.hitlist = new ArrayList <EntityType> ();
		// Ignore List
		this.ignoreList = EnumSet.noneOf(EntityType.class);
		// Mob flags
		this.mobFlags = EnumSet.noneOf(MobFlag.class);
		// Monster Stats
		this.mobStats = new EnumMap <MonsterStat, Double> (MonsterStat.class);
		// Mounted mob
		this.mount = null;
		// Custom loot
		this.loot = new ArrayList <MonsterDrop> ();
		// Tipped arrow
		this.tippedArrow = null;
		// Boss theme
		this.bossTheme = null;
		// Generic monster classifier
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.COMMON);
	}
	
	/** Loads new instances of custom monster skills into an active skills list for that mob
	 * 	@param skills - A list of skills that are to be loaded with mob-specific custom skills */
	public abstract void loadCustomSkills (List <AbstractSkill> skills);
	
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
	
	/** Returns true if the monster contains the passed Monster Stat
	 * 	@param stat - The Monster stat being checked */
	public boolean containsStat (MonsterStat stat)
	{
		return this.mobStats.containsKey(stat);
	}
	
	/** Sets a monster's stat value to a new value
	 * 	@param stat - Monster stat we are changing
	 *  @param value - Value we are setting the stat to */
	public void setStat (MonsterStat stat, double value)
	{
		this.mobStats.put(stat, value);
	}
	
	/** Sets a monster's stat value to a new value as an enum
	 * 	@param stat - Monster stat we are changing
	 *  @param value - Value we are setting the stat to */
	public void setStat (MonsterStat stat, Enum<?> value)
	{
		this.mobStats.put(stat, (double) value.ordinal());
	}
	
	/** Returns a monster's stat value based on the passed MonsterStat type
	 *  @param stat - Type of stat retrieved from this monster
	 *  @return The stat itself or null if the stat does not exist.  */
	public Double getStat (MonsterStat stat)
	{
		if (this.mobStats.containsKey(stat))
		{
			return this.mobStats.get(stat);
		}
		return null;
	}
	
	/** Returns the mob's stat container */
	public EnumMap <MonsterStat, Double> getStatContainer ()
	{
		return this.mobStats;
	}
	
	/** Return the drop rates for each item in the mob's inventory */
	public float[] getDropRates ()
	{
		return this.dropRates;
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
	
	/** Returns the monster's active potion effects as an ArrayList */
	public ArrayList <PotionEffect> getEffects ()
	{
		return this.effects;
	}
	
	/** Returns this monster's spawnweight */
	public int getSpawnWeight ()
	{
		if (this.mobStats.containsKey(MonsterStat.SPAWN_WEIGHT))
		{
			return this.mobStats.get(MonsterStat.SPAWN_WEIGHT).intValue();
		}
		return -1;
	}
	
	/** Sets this monster's spawn weight to a new value */
	public void setSpawnWeight (int spawnWeight)
	{
		this.mobStats.put(MonsterStat.SPAWN_WEIGHT, (double) spawnWeight);
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
	public EnumSet <Biome> getSpawnBiomes ()
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
	
	/** Adds a list of mob flags that should be applied to the entity upon spawning */
	public void addMobFlags (MobFlag... flags)
	{
		for (MobFlag flag : flags)
		{
			this.mobFlags.add(flag);
		}
	}
	
	/** Adds a list of monster drop instances to this custom monster's loot table */
	public void addMonsterDrops (MonsterDrop... drops)
	{
		for (MonsterDrop drop : drops)
		{
			this.loot.add(drop);
		}
	}
	
	/** Americanizes a name by applying the USA colors to every character in a string
	 *  in an alternating pattern */
	public String americanizeText (String str)
	{	
		return StringUtil.alternateTextColors(str, TextPattern.CHARACTER, 
				ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
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
	public EnumSet <Faction> getFactions ()
	{
		return this.factions;
	}
	
	/** Returns the list of factions this entity should hunt */
	public EnumSet <Faction> getRivalFactions ()
	{
		return this.rivalFactions;
	}
	
	/** Returns the list of  entities this monster should hunt */
	public ArrayList <EntityType> getHitList ()
	{
		return this.hitlist;
	}
	
	/** Returns the hashset of entities this monster should ignore */
	public EnumSet <EntityType> getIgnoreList ()
	{
		return this.ignoreList;
	}
	
	/** Returns this entity's set of mob flags */
	public EnumSet <MobFlag> getMobFlags ()
	{
		return this.mobFlags;
	}
	
	/** Returns this entity's custom monster drops */
	public ArrayList <MonsterDrop> getMonsterDrops ()
	{
		return this.loot;
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
	
	/** Returns true if this mob equipment instance contains
	 *  the passed Monster Flag */
	public boolean containsFlag (MobFlag flag)
	{
		return this.mobFlags.contains(flag);
	}
	
	/** Checks if this monster is in the passed custom mob's list of rivaling factions
	 *   @param mob - Monster's mob equipment being checked against this instance */
	public boolean isRivalsOf (MobEquipment mob)
	{
		EnumSet <Faction> rivalingFactions = mob.getRivalFactions();
		for (Faction faction : rivalingFactions)
		{
			if (this.factions.contains(faction))
			{
				return true;
			}
		}
		return false;
	}
	
	/** Checks if this monster is allies with this mob
	 *  @param entity The LivingEntity holding this MobEquipment instance
	 *  @param other The ally being checked
	 * 	@param otherEquipment The ally's monster's MonEquipment instance */
	public boolean isAlliesOf (LivingEntity entity, LivingEntity other, MobEquipment otherEquipment)
	{
		boolean result = false;
		boolean noFactions = false;
		
		// If damaging itself, return true
		if (other == entity)
		{
			return true;
		}
		
		// Do not target players with active ignore metadata timestamps
		if (other instanceof Player)
		{
			IgnorePlayerMetadata metadata = new IgnorePlayerMetadata ();
			if (metadata.canIgnorePlayer((Player) other))
			{
				return true;
			}
		}
		if (otherEquipment != null)
		{
			// Check if the entity is in the same faction as the other entity.
			// If so, immediately return true
			EnumSet <Faction> factions = otherEquipment.getFactions();
			noFactions = this.factions.isEmpty() && factions.isEmpty();
			for (Faction faction : factions)
			{
				if (this.factions.contains(faction))
				{
					return true;
				}
			}
			// Check if the entity is rivals with the other entity. If so, return false
			if (this.isRivalsOf(otherEquipment))
			{
				return false;
			}
			result = otherEquipment.getIgnoreList().contains(entity.getType());
		}
		
		// Allows neutral mobs and custom mobs without anything in their hitlist
		// to be allies with other non-factioned, non-player mobs
		if (noFactions ||(otherEquipment == null && other.getType() != EntityType.PLAYER) ||
				this.getIgnoreList().contains(EntityType.PLAYER))
		{
			result |= !this.hitlist.contains(other.getType());
		}
			
		return this.ignoreList.contains(other.getType()) || result;
	}
	
	/** Returns true if this monster has a mounted mob */
	public boolean hasMountedMob ()
	{
		return this.mount != null;
	}
	
	/** Returns this monster's mounted mob if it exists */
	public MountedMob getMountedMob ()
	{
		return this.mount;
	}
	
	/** Returns true if this monster contains a custom tipped arrow to shoot with */
	public boolean hasTippedArrow ()
	{
		return this.tippedArrow != null;
	}
	
	/** Returns the monster's custom tipped arrow */
	public TippedArrow getTippedArrow ()
	{
		return this.tippedArrow;
	}
	
	/** Returns the monster's boss theme */
	public MusicType getBossTheme ()
	{
		return this.bossTheme;
	}
	
	/** Returns true if this mob contains a boss theme */
	public boolean containsBossTheme()
	{
		return this.bossTheme != null;
	}
	
	/** Returns the fair spawn threshold for this monster */
	public Double getFairSpawnThreshold ()
	{
		return MonsterClassifier.values()[this.getStat(MonsterStat.MONSTER_CLASSIFIER).intValue()].getThreshold();
	}
	
	/** Return the equipment's monster type identifier as a string. */
	@Override
	public String toString ()
	{
		return this.mobType.toString();
	}
}
