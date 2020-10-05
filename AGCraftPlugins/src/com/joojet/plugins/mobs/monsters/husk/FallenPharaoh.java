package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.RoyalGoldBoots;
import com.joojet.plugins.mobs.equipment.chest.RoyalGoldChestplate;
import com.joojet.plugins.mobs.equipment.head.PharaohHead;
import com.joojet.plugins.mobs.equipment.leggings.RoyalGoldLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PharaohStaff;
import com.joojet.plugins.mobs.monsters.factions.RareMob;

public class FallenPharaoh extends RareMob
{
	public FallenPharaoh ()
	{
		super (MonsterType.FALLEN_PHARAOH);
		this.addBiomes(Biome.THE_VOID);
		
		this.name = "Fallen Pharaoh";
		this.color = ChatColor.LIGHT_PURPLE;

		this.helmet = new PharaohHead (this.color);
		this.chestplate = new RoyalGoldChestplate (this.color);
		this.leggings = new RoyalGoldLeggings (this.color);
		this.boots = new RoyalGoldBoots (this.color);
		this.weapon = new PharaohStaff (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 27.0);
	}
}
