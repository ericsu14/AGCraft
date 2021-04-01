package com.joojet.plugins.mobs.monsters;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;
import com.joojet.plugins.mobs.util.WeightedList;

public abstract class MonsterTypes 
{
	/** Stores a list of monsters the Mob Equipment tied to this class can equip */
	protected ArrayList <EntityType> supportedEntities;
	/** Stores custom mob equipment tied under this class */
	protected ArrayList <MobEquipment> equipmentList;
	/** RNG used for selecting a random monster when getRandomEquipment is called */
	protected Random random;
	/** Total number of custom mob equipment tied to this class */
	protected int size;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Search trie to initialize summoning scroll instances */
	protected SummoningScrollInterpreter summonTypeInterpreter;
	
	public MonsterTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, EntityType... entities)
	{
		this.equipmentList = new ArrayList <MobEquipment> ();
		this.supportedEntities = new ArrayList <EntityType> ();
		this.random = new Random ();
		this.size = 0;
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.summonTypeInterpreter = summonTypeInterpreter;
		
		for (EntityType entity : entities)
		{
			this.supportedEntities.add(entity);
		}
	}
	
	/** Adds in a new monster into the moblist.
	 * 		@param equipment - Monster's equipment class
	 * 		@param weight - Weight used to amplify the monster's spawn chances (higher = more frequently)s */
	public void addEquipment (MobEquipment equipment, int weight)
	{
		equipment.setSpawnWeight(weight);
		equipmentList.add(equipment);
		this.monsterTypeInterpreter.insertWord(equipment.toString(), equipment);
		// Registers the entity as a new summoning scroll
		if (!this.supportedEntities.isEmpty())
		{
			this.summonTypeInterpreter.insertWord(equipment.toString(), new SummoningScroll (equipment, this.supportedEntities.get(0)));
		}
		++this.size;
	}
	
	/** Returns a random mob that spawns within a specific biome
	 * 		@param biome - The biome the monster spawns in */
	public MobEquipment getRandomEquipment (Biome biome, MobSkillRunner mobSkillRunner)
	{
		EnumSet <Biome> spawnBiomes;
		// ArrayList <WeightedMob> mobList = new ArrayList <WeightedMob> ();
		WeightedList <WeightedMob, MobEquipment> mobList = new WeightedList <WeightedMob, MobEquipment> ();
		
		for (MobEquipment mob : equipmentList)
		{
			spawnBiomes = mob.getSpawnBiomes();
			/* Add this mob to the monster spawn list if their spawn biomes either contain THE_VOID
			 * or the passed biome. */
			if ((spawnBiomes.contains(Biome.THE_VOID) || spawnBiomes.contains(biome))
					&& !mobSkillRunner.reachedSpawnLimit(mob))
			{
				mobList.addEntry(new WeightedMob (mob, mob.getSpawnWeight()));
			}
		}
		
		// Binary searches the list with a random number and returns a random mob
		if (!mobList.isEmpty())
		{
			return mobList.getRandomEntry();
		}
		return null;
	}
	
	/** Returns the total number of custom monsters appended to this list */
	public int getSize ()
	{
		return this.size;
	}
	
	/** Returns the entities in which the mob equipment tied to this class can equip */
	public ArrayList <EntityType> getSupportedEntities ()
	{
		return this.supportedEntities;
	}
}
