package com.joojet.plugins.mobs.monsters.factions;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class UCLAFaction extends MobEquipment
{
	public UCLAFaction (MonsterType type)
	{
		super (type);
		this.addFactions(Faction.UCLA);
		this.addRivalFactions(Faction.USC, Faction.PHANTOM, Faction.DOOM_GUY);
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON,
				EntityType.PLAYER, EntityType.PHANTOM, EntityType.WITHER_SKELETON);
		this.addEntitiesToIgnoreList(EntityType.CREEPER);
	}
}
