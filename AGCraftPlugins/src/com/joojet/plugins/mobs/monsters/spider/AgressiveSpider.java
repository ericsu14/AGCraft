package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.weapons.SpiderTooth;
import com.joojet.plugins.mobs.monsters.factions.classifications.UncommonMob;

public class AgressiveSpider extends UncommonMob
{
	public AgressiveSpider ()
	{
		super (MonsterType.AGRESSIVE_SPIDER);
		this.setStat(MonsterStat.HEALTH, 20.0);
		this.name = "Agressive Spider";
		this.color = ChatColor.GREEN;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.effects.add(CustomPotionEffect.RESISTANCE.getPotionEffect());
		
		// Weapon
		this.weapon = new SpiderTooth (this.color);
		
	}
}
