package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.offhand.WitheringArrow;
import com.joojet.plugins.mobs.equipment.weapons.AngelOfDeath;
import com.joojet.plugins.mobs.monsters.factions.EpicMob;

public class SoulEater extends EpicMob 
{
	public SoulEater ()
	{
		super (MonsterType.SOUL_EATER);
		this.name = "Soul Eater";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.SPEED,
				CustomPotionEffect.FIRE_RESISTANCE);
		
		// Weapon
		this.weapon = new AngelOfDeath (this.color);
		// Offhand
		this.offhand = new WitheringArrow (this.color);
		// Helmet
		this.helmet = new DarkNetheriteHelmet (this.color);
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.setStat(MonsterStat.EXPERIENCE, 25.0);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 12.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.30);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.60);
	}
}
