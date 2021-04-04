package com.joojet.plugins.mobs.monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;
import com.joojet.plugins.mobs.util.WeightedList;

public abstract class MonsterTypes implements Cloneable
{
	/** Stores a list of monsters the Mob Equipment tied to this class can equip */
	protected List <EntityType> supportedEntities;
	/** Stores custom mob equipment tied under this class */
	protected List <MobEquipment> equipmentList;
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
	
	/** Returns a clone of this MonsterType instance */
	@Override
	public MonsterTypes clone () throws CloneNotSupportedException
	{
		MonsterTypes copy = (MonsterTypes) super.clone();
		copy = new MonsterTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter, 
				Arrays.copyOf(this.supportedEntities.toArray(), this.supportedEntities.size(), EntityType[].class)) {};
		copy.mergeMonsterTypes(this);
		return copy;
	}
	
	
	/** Returns the total number of custom monsters appended to this list */
	public int getSize ()
	{
		return this.size;
	}
	
	/** Returns the entities in which the mob equipment tied to this class can equip */
	public List <EntityType> getSupportedEntities ()
	{
		return this.supportedEntities;
	}
	
	/** Returns the internal mob equipment list */
	public List <MobEquipment> getMobEquipmentList ()
	{
		return this.equipmentList;
	}
	
	/** Merges the MobEquipment list of another MonsterType instance into this instance's MobEquipment list
	 *  @param mobTypes The MonsterType instance to be merged */
	public void mergeMonsterTypes (MonsterTypes mobTypes)
	{
		this.equipmentList.addAll (mobTypes.getMobEquipmentList());
		size += mobTypes.getSize();
	}
}
