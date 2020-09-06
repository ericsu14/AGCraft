package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.TrojanWarrior;

public class SummonTrojanWarrior extends SummoningScroll 
{
	public SummonTrojanWarrior ()
	{
		super (new TrojanWarrior(), EntityType.SKELETON);
	}
}
