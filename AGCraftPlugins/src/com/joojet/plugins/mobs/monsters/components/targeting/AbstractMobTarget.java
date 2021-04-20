package com.joojet.plugins.mobs.monsters.components.targeting;

import java.util.EnumSet;

import org.bukkit.entity.EntityType;

public class AbstractMobTarget 
{
	/** A list of entities this monster should hunt either in addition to, but not including
	 *  the entities the monster naturally hunts in vanilla Minecraft. */
	protected EnumSet <EntityType> hitlist;
	/** A list of entities that this monster should ignore, meaning that they will never
	 *  become hostile to that entity. The entity in question will 
	 *  also never be hostile to this mob. */
	protected EnumSet <EntityType> ignoreList;
	
	public AbstractMobTarget ()
	{
		this.hitlist = EnumSet.noneOf(EntityType.class);
		this.ignoreList = EnumSet.noneOf(EntityType.class);
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
	
	/** Returns the list of entities this monster should hunt */
	public EnumSet <EntityType> getHitList ()
	{
		return this.hitlist;
	}
	
	/** Returns the hashset of entities this monster should ignore */
	public EnumSet <EntityType> getIgnoreList ()
	{
		return this.ignoreList;
	}
}
