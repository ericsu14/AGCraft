package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.UCLABearTamer;

public class SummonUCLABearTamer extends BossScroll 
{
	public SummonUCLABearTamer ()
	{
		super (new UCLABearTamer(), EntityType.SKELETON);
	}
}
