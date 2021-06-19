package com.joojet.plugins.mobs.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class InjectCustomGoalsToEntityEvent extends Event 
{
	/** The living entity attached to this event */
	protected Entity entity;
	/** Event handler list */
	private static final HandlerList HANDLERS = new HandlerList();
	
	public InjectCustomGoalsToEntityEvent (Entity entity)
	{
		this.entity = entity;
	}
	
	/** Gets the entity attached to this event */
	public Entity getEntity ()
	{
		return this.entity;
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
