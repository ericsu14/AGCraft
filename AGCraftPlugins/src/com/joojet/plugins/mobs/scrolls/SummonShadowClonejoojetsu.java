package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie.UltimateBadassZombie;

public class SummonShadowClonejoojetsu extends BossScroll
{
	public SummonShadowClonejoojetsu ()
	{
		super (new UltimateBadassZombie(), EntityType.ZOMBIE);
	}
}
