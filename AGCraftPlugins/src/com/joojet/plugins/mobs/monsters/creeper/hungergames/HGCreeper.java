package com.joojet.plugins.mobs.monsters.creeper.hungergames;

import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class HGCreeper extends MobEquipment 
{
	public HGCreeper() 
	{
		super(MonsterType.HG_CREEPER);
		this.addMobFlags(MobFlag.DISABLE_EXPLOSION_GRIEFING);
		this.addBiomes(Biome.THE_VOID);
	}
}
