package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightChainmailBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedChainmailChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedChainmailHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedChainmailLeggings;
import com.joojet.plugins.mobs.equipment.weapons.EnhancedStoneSword;
import com.joojet.plugins.mobs.monsters.factions.UncommonMob;

public class UncommonZombie extends UncommonMob 
{
	public UncommonZombie ()
	{
		super (MonsterType.UNCOMMON_ZOMBIE);
		this.name = "Bulky Zombie";
		this.color = ChatColor.GREEN;
		this.setStat(MonsterStat.HEALTH, 16.0);
		
		this.addBiomes(Biome.THE_VOID);
		
		// Weapon
		this.weapon = new EnhancedStoneSword (this.color);
		// Helmet
		this.helmet = new ReinforcedChainmailHelmet (this.color);
		// Chestplate
		this.chestplate = new ReinforcedChainmailChestplate (this.color);
		// Leggings
		this.leggings = new ReinforcedChainmailLeggings (this.color);
		// Boots
		this.boots = new LightweightChainmailBoots (this.color);
	}
}
