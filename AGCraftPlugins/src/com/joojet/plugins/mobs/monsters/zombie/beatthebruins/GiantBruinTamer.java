package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.giant.beatthebruins.GiantBruin;

public class GiantBruinTamer extends UCLAJock
{
	public GiantBruinTamer ()
	{
		super();
		this.name = "Giant Bruin Tamer";
		this.addBiomes(Biome.THE_VOID);
		this.mount = new MountedMob (EntityType.GIANT, new GiantBruin());
	}
}
