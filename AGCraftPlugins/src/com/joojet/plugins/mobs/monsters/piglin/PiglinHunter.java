package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.RoyalGoldBoots;
import com.joojet.plugins.mobs.equipment.head.PiglinHunterHelmet;
import com.joojet.plugins.mobs.equipment.weapons.PiglinCrossbow;
import com.joojet.plugins.mobs.equipment.weapons.ShotBow;
import com.joojet.plugins.mobs.monsters.factions.classifications.RareMob;

public class PiglinHunter extends RareMob 
{
	public PiglinHunter ()
	{
		super (MonsterType.PIGLIN_HUNTER);
		this.name = "Piglin Hunter";
		this.color = ChatColor.BLUE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new PiglinHunterHelmet (this.color);
		this.boots = new RoyalGoldBoots (this.color);
		this.weapon = new PiglinCrossbow (ChatColor.BLUE);
		
		this.setStat(MonsterStat.EXPERIENCE, 18.0);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 6.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.30);
		
		this.addMonsterDrops(new MonsterDrop (new ShotBow (ChatColor.GOLD), 0.025));
	}
}
