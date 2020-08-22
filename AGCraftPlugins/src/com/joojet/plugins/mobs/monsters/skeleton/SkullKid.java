package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.SkullKidBoots;
import com.joojet.plugins.mobs.equipment.chest.SkullKidChest;
import com.joojet.plugins.mobs.equipment.head.SkullKidHelmet;
import com.joojet.plugins.mobs.equipment.leggings.SkullKidPants;
import com.joojet.plugins.mobs.equipment.offhand.CursedArrow;
import com.joojet.plugins.mobs.equipment.weapons.ATerribleFate;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class SkullKid extends MobEquipment 
{
	public SkullKid ()
	{
		super (MonsterType.SKULL_KID);
		this.name = "Skull Kid";
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 100.0);
		this.color = ChatColor.DARK_RED;
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING, 
				MobFlag.HUNT_ON_SPAWN, MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER);
		
		this.setDropRates(0.25f, 0.10f, 0.10f, 0.10f, 0.05f, 0.10f);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.SPEED);
		
		this.helmet = new SkullKidHelmet (this.color);
		this.chestplate = new SkullKidChest (this.color);
		this.leggings = new SkullKidPants (this.color);
		this.boots = new SkullKidBoots (this.color);
		this.weapon = new ATerribleFate (this.color);
		this.offhand = new CursedArrow (this.color);
	}
}
