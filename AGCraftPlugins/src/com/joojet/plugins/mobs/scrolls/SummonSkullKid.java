package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.SkullKid;

public class SummonSkullKid extends BossScroll 
{
	public SummonSkullKid ()
	{
		super (new SkullKid (), EntityType.SKELETON);
	}
}
