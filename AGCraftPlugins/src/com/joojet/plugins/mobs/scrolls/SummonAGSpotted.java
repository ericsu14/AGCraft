package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.UltimateBadassSkeleton;

public class SummonAGSpotted extends SummoningScroll
{
	public SummonAGSpotted ()
	{
		super (new UltimateBadassSkeleton (), EntityType.SKELETON);
	}
}
