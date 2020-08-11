package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.weapons.SpiderTooth;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class AgressiveSpider extends MobEquipment
{
	public AgressiveSpider ()
	{
		super (MonsterType.AGRESSIVE_SPIDER);
		this.health = 20;
		this.name = "Agressive Spider";
		this.color = ChatColor.GREEN;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.effects.add(CustomPotionEffect.RESISTANCE.getPotionEffect());
		
		// Weapon
		this.weapon = new SpiderTooth (this.color);
		
	}
}
