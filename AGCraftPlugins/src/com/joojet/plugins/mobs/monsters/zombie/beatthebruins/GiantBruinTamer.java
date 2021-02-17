package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.monsters.giant.beatthebruins.GiantBruin;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.LazerBeamAttack;

public class GiantBruinTamer extends UCLAFaction
{
	public GiantBruinTamer ()
	{
		super(MonsterType.GIANT_BRUIN_TAMER);
		this.name = StringUtil.alternateTextColors("Giant Bruin Tamer", TextPattern.WORD, 
				ChatColor.AQUA, ChatColor.GOLD);
		this.addBiomes(Biome.PLAINS, Biome.DESERT, Biome.DESERT_HILLS, Biome.DESERT_LAKES,
				Biome.BADLANDS, Biome.BADLANDS_PLATEAU, Biome.ERODED_BADLANDS, Biome.SAVANNA_PLATEAU,
				Biome.SNOWY_TUNDRA, Biome.FROZEN_RIVER, Biome.SNOWY_BEACH,
				Biome.GRAVELLY_MOUNTAINS, Biome.MOUNTAINS, Biome.STONE_SHORE,
				Biome.SUNFLOWER_PLAINS, Biome.SWAMP, Biome.JUNGLE_EDGE, Biome.MODIFIED_JUNGLE_EDGE,
				Biome.BEACH, Biome.ERODED_BADLANDS, Biome.SHATTERED_SAVANNA_PLATEAU,
				Biome.SHATTERED_SAVANNA, Biome.MODIFIED_GRAVELLY_MOUNTAINS);
		
		this.mount = new MountedMob (EntityType.GIANT, new GiantBruin());
		this.weapon = new BruinSword (this.color);
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		
		this.setStat(MonsterStat.Y_LIMIT, 55.0);
		this.setStat(MonsterStat.HEALTH, 30.0);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.LEGENDARY);
		this.setStat(MonsterStat.EXPERIENCE, 24.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 8.0);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		super.loadCustomSkills(skills);
		skills.add(new LazerBeamAttack (24, 12, Integer.MAX_VALUE, 16, 80));
	}
}
