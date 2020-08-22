package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

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
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class DoomGuy extends MobEquipment
{
	public DoomGuy ()
	{
		super (MonsterType.DOOM_GUY);
		this.name = "The Doom Slayer";
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.05f, 0.15f);
		this.color = ChatColor.DARK_RED;
		this.setStat(MonsterStat.HEALTH, 50.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 125.0);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.UNDEAD_HEAL);
		
		this.addFactions(Faction.DOOM_GUY);
		this.addRivalFactions(Faction.NETHER, Faction.USC, Faction.UCLA);
		this.addTargetsToHitList(EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER_SKELETON,
				EntityType.MAGMA_CUBE, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.CREEPER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.PLAYER);
		
		this.addMobFlags(MobFlag.SPAWN_LIGHTNING, MobFlag.SHOW_NAME, MobFlag.BOSS_BAR,
				MobFlag.PERSISTENT_ATTACKER);
		
		this.helmet = new DoomSlayerHead (this.color);
		this.chestplate = new DoomChest (this.color);
		this.leggings = new DoomGuyLegs (this.color);
		this.boots = new DoomGuyFeet (this.color);
		this.weapon = new DoomBlade (this.color);
		this.offhand = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		
		this.setStat(MonsterStat.EXPERIENCE, 250.0);
	}
}
