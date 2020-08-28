package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.chest.SnowballHeart;

public class Snowball extends AlliedMob
{
	public Snowball ()
	{
		super (MonsterType.SNOWBALL);
		this.color = ChatColor.AQUA;
		this.name = "Snowball";
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		this.chestplate = new SnowballHeart (this.color);
		this.boots = new LetItGo (this.color);
		this.setDropRates(0.0f, 1.15f, 0.0f, 0.0f, 0.0f, 0.0f);
	}
}
