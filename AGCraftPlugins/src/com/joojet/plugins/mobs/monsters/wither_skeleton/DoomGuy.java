package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.DoomGuyFeet;
import com.joojet.plugins.mobs.equipment.chest.DoomChest;
import com.joojet.plugins.mobs.equipment.head.DoomSlayerHead;
import com.joojet.plugins.mobs.equipment.leggings.DoomGuyLegs;
import com.joojet.plugins.mobs.equipment.weapons.DoomBlade;
import com.joojet.plugins.mobs.monsters.factions.classifications.MythicMob;
import com.joojet.plugins.music.enums.MusicType;

public class DoomGuy extends MythicMob
{
	public DoomGuy ()
	{
		super (MonsterType.DOOM_GUY);
		this.name = StringUtil.alternateTextColors("The Doom Slayer", TextPattern.WORD, 
				ChatColor.DARK_GRAY, ChatColor.DARK_RED, ChatColor.DARK_GREEN);
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.05f, 1.00f);
		this.color = ChatColor.DARK_RED;
		this.setStat(MonsterStat.HEALTH, 50.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 30.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 125.0);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.UNDEAD_HEAL, CustomPotionEffect.STRENGTH);
		
		this.addFactions(Faction.DOOM_GUY);
		this.addRivalFactions(Faction.NETHER, Faction.USC, Faction.UCLA);
		this.addTargetsToHitList(EntityType.ZOMBIFIED_PIGLIN,
				EntityType.MAGMA_CUBE, EntityType.HOGLIN, EntityType.CREEPER, EntityType.ZOMBIE, EntityType.SKELETON,
				EntityType.WITHER_SKELETON, EntityType.WITCH, EntityType.HOGLIN, EntityType.HUSK, EntityType.STRAY,
				EntityType.POLAR_BEAR);
		
		this.addMobFlags(MobFlag.SPAWN_LIGHTNING, MobFlag.SHOW_NAME, MobFlag.BOSS_BAR,
				MobFlag.PERSISTENT_ATTACKER);
		
		this.helmet = new DoomSlayerHead (this.color);
		this.chestplate = new DoomChest (this.color);
		this.leggings = new DoomGuyLegs (this.color);
		this.boots = new DoomGuyFeet (this.color);
		this.weapon = new DoomBlade (this.color);
		this.offhand = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 1.00, 2, 8),
				new MonsterDrop (Material.NETHERITE_INGOT, 1.00, 1, 1),
				new MonsterDrop (Material.DIAMOND, 0.75, 1, 4),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 1.00, 5, 7),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 1.00, 2, 6));
		
		this.bossTheme = MusicType.DOOM_GUY;
		this.setStat(MonsterStat.EXPERIENCE, 600.0);
	}
}
