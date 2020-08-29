package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.SpiritOfTroy;

public class SummonSpiritOfTroy extends BossScroll 
{
	public SummonSpiritOfTroy ()
	{
		super (new SpiritOfTroy (), EntityType.HUSK);
	}
}
