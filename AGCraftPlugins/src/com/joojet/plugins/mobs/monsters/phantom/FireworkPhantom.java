package com.joojet.plugins.mobs.monsters.phantom;

import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public class FireworkPhantom extends MobEquipment
{
	public FireworkPhantom ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.health = 1;
		this.name = this.americanizeText("Shoot Me!");
	}
}
