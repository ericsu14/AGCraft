package com.joojet.plugins.mobs.monsters;

import org.bukkit.entity.EntityType;

/** Used to define a custom mob that is rideable by another mob */
public class MountedMob 
{
	/** Entity type applied for the rideable monster */
	protected EntityType mountEntityType;
	/** Custom mob data used for this rideble monster*/
	protected MobEquipment mountEquipment;
	
	public MountedMob (EntityType mountEntityType, MobEquipment mountEquipment)
	{
		this.mountEntityType = mountEntityType;
		this.mountEquipment = mountEquipment;
	}
	
	public MobEquipment getMobEquipment ()
	{
		return this.mountEquipment;
	}
	
	public EntityType getEntityType ()
	{
		return this.mountEntityType;
	}
}
