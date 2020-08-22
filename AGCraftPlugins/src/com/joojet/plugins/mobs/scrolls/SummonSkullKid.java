package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.SkullKid;

public class SummonSkullKid extends SummoningScroll 
{
	public SummonSkullKid ()
	{
		super (new SkullKid (), EntityType.SKELETON);
	}
}
