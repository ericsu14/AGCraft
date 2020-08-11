package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.DesertSandals;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class BadlandsBandit extends MobEquipment
{
	public BadlandsBandit ()
	{
		super (MonsterType.BADLANDS_BANDIT);
		this.name = "Badlands Bandit";
		this.color = ChatColor.LIGHT_PURPLE;
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.addBiomes(Biome.BADLANDS, 
				Biome.BADLANDS_PLATEAU, 
				Biome.SAVANNA, 
				Biome.SAVANNA_PLATEAU, 
				Biome.SHATTERED_SAVANNA, 
				Biome.SHATTERED_SAVANNA_PLATEAU);
		
		this.health = 30;
		
		this.boots = new DesertSandals (this.color);
		this.weapon = new SpiritualFantasy (this.color);
	}
}
