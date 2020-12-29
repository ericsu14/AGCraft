package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.wither_skeleton.SoulObliterator;

public class SummonSoulObliterator extends BossScroll 
{
	public SummonSoulObliterator ()
	{
		super (new SoulObliterator (), EntityType.WITHER_SKELETON);
	}
}
