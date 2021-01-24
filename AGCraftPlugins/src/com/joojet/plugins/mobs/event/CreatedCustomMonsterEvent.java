package com.joojet.plugins.mobs.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.joojet.plugins.mobs.monsters.MobEquipment;

/** An event that fires upon sucessful creation of a custom AGCraft monster */
public class CreatedCustomMonsterEvent extends Event 
{
	/** The living entity attached to this event */
	protected LivingEntity entity;
	/** The mob-equipment used to construct this monster */
	protected MobEquipment equipment;
	/** Event handler list */
	private static final HandlerList HANDLERS = new HandlerList();
	
	/** Constructs a new CreatedCustomMonster event, which fires at the end of a successful custom monster conversion.
	 *  @param entity - Entity that was converted into a custom monster
	 *  @param equipment - MobEquipment instance used to convert the monster */
	public CreatedCustomMonsterEvent (LivingEntity entity, MobEquipment equipment)
	{
		super();
		this.entity = entity;
		this.equipment = equipment;
	}
	
	/** Returns the living entity that was just transformed into a custom monster */
	public LivingEntity getEntity ()
	{
		return this.entity;
	}
	
	/** Returns the mob equipment instance used to build this custom monster */
	public MobEquipment getMobEquipment ()
	{
		return this.equipment;
	}
	
	@Override
	public HandlerList getHandlers() 
	{
		return HANDLERS;
	}
	
    public static HandlerList getHandlerList() 
    {
        return HANDLERS;
    }

}
