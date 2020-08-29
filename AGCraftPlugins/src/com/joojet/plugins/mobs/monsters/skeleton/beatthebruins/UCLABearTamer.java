package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.PoisonousArrow;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.monsters.polar_bear.beatthebruins.TheBruinBear;

public class UCLABearTamer extends UCLAFaction 
{
	public UCLABearTamer ()
	{
		super (MonsterType.UCLA_BEAR_TAMER);
		this.name = "The " + ChatColor.AQUA + "U" + ChatColor.GOLD + "C"
				+ ChatColor.AQUA + "L" + ChatColor.GOLD + "A" + ChatColor.AQUA + " Bear" + 
				ChatColor.GOLD + " Tamer";
		this.setStat(MonsterStat.EXPERIENCE, 50.0);
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.addMobFlags(MobFlag.SPAWN_LIGHTNING, MobFlag.HUNT_ON_SPAWN,
				MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME);
		this.color = ChatColor.AQUA;
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.weapon = new SpiritualFantasy (ChatColor.GOLD);
		this.offhand = new PoisonousArrow (ChatColor.GREEN);
		this.mount = new MountedMob (EntityType.POLAR_BEAR, new TheBruinBear());
	}
}
