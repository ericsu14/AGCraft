package com.joojet.plugins.mobs.monsters.zombie;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.FireworkDrop;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BarneyFeet;
import com.joojet.plugins.mobs.equipment.chest.BarneyChest;
import com.joojet.plugins.mobs.equipment.head.BarneyHead;
import com.joojet.plugins.mobs.equipment.leggings.BarneyLegs;
import com.joojet.plugins.mobs.equipment.offhand.BarneyTotem;
import com.joojet.plugins.mobs.equipment.weapons.BarneyDagger;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.utility.AgressiveTeleportSkill;
import com.joojet.plugins.music.enums.MusicType;

public class BarneyTheDinosaur extends LegendaryMob
{
	public BarneyTheDinosaur ()
	{
		super (MonsterType.BARNEY_THE_DINOSAUR);
		this.setStat(MonsterStat.HEALTH, 10.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 50.0);
		
		this.addBiomes(Biome.FLOWER_FOREST, Biome.DARK_FOREST, Biome.DARK_FOREST_HILLS,
				Biome.SWAMP, Biome.SWAMP_HILLS, Biome.JUNGLE, Biome.JUNGLE_HILLS, Biome.JUNGLE_EDGE,
				Biome.GRAVELLY_MOUNTAINS, Biome.SUNFLOWER_PLAINS);
		this.addPotionEffect(CustomPotionEffect.SPEED,
				CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.UNDEAD_HEAL);
		this.addMobFlags(MobFlag.HUNT_ON_SPAWN, MobFlag.SPAWN_LIGHTNING, MobFlag.SHOW_NAME,
				MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER);
		
		this.name = "Barney the Dinosaur";
		this.color = ChatColor.DARK_PURPLE;
		this.setDropRates(0.01f, 0.01f, 0.01f, 0.01f, 0.01f, 1.00f);
		this.helmet = new BarneyHead (this.color);
		this.chestplate = new BarneyChest (this.color);
		this.leggings = new BarneyLegs (this.color);
		this.boots = new BarneyFeet (this.color);
		this.weapon = new BarneyDagger (this.color);
		this.offhand = new BarneyTotem (this.color);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.15, 1, 3),
				new FireworkDrop (0.25, 16, 16),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.35, 1, 1),
				new MonsterDrop (new BarneyTotem (this.color), 0.01));
		
		this.setStat(MonsterStat.EXPERIENCE, 200.0);
		this.bossTheme = MusicType.BARNEY;
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new RageSkill (1, 120, 0.35));
		skills.add(new AgressiveTeleportSkill (156, 10, Integer.MAX_VALUE, 2));
		skills.add(new ThundagaSkill (32, 20, Integer.MAX_VALUE, 16, 4.0f, 6, 60, 0.70));
	}
}
