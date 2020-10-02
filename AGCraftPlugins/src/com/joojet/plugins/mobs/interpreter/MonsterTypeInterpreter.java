package com.joojet.plugins.mobs.interpreter;

import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataHolder;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class MonsterTypeInterpreter extends AbstractInterpreter <MobEquipment>
{
	public MonsterTypeInterpreter ()
	{
		super ();
	}
	
	/** Finds and returns a LivingEntity's custom mob equipment object.
	 *  Returns null if the entity does not have custom mob metadata.
	 *  @param entity - The living entity where we are extracting its custom mob equipment from */
	public MobEquipment getMobEquipmentFromEntity (LivingEntity entity)
	{
		// First check if the entity has custom mob metadata
		if (entity == null)
		{
			return null;
		}
		
		PersistentDataHolder holder = (PersistentDataHolder) entity;
		String entityMeta = new MonsterTypeMetadata ().getStringMetadata(holder);
		if (entityMeta == null || entityMeta.isEmpty())
		{
			return null;
		}
		
		// Extract custom metadata from the entity and use its string to lookup its own mob equipment
		MobEquipment entityEquipment = this.searchTrie(entityMeta);
		
		return entityEquipment;
	}
}
