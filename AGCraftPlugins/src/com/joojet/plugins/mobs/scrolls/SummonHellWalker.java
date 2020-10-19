package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.wither_skeleton.HellWalker;

public class SummonHellWalker extends BossScroll
{
	public SummonHellWalker() 
	{
		super(new HellWalker (), EntityType.WITHER_SKELETON);
	}
}
