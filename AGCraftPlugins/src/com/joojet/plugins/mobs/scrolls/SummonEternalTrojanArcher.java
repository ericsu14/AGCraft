package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.EternalTrojanArcher;

public class SummonEternalTrojanArcher extends SummoningScroll 
{
	public SummonEternalTrojanArcher ()
	{
		super (new EternalTrojanArcher(), EntityType.SKELETON);
	}
}
