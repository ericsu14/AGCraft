package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.DesertSandals;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;

public class BadlandsBandit extends EpicMob
{
	public BadlandsBandit ()
	{
		super (MonsterType.BADLANDS_BANDIT);
		this.name = "Badlands Bandit";
		this.color = ChatColor.LIGHT_PURPLE;
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.setStat(MonsterStat.HEALTH, 30.0);
		
		this.addBiomes(Biome.BADLANDS,
				Biome.SAVANNA, 
				Biome.SAVANNA_PLATEAU);
		
		this.boots = new DesertSandals (this.color);
		this.weapon = new SpiritualFantasy (this.color);
	}
}
