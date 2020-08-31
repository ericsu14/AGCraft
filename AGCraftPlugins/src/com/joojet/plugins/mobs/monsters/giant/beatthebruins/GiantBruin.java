package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class GiantBruin extends UCLAFaction
{
	public GiantBruin ()
	{
		super (MonsterType.GIANT_BRUIN);
		this.color = ChatColor.AQUA;
		this.name = "Giant" + ChatColor.GOLD + " Bruin";
		this.setStat(MonsterStat.HEALTH, 200.0);
		this.setStat(MonsterStat.EXPERIENCE, 1000.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 150.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 4.0);
		this.weapon = new BruinSword (this.color);
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		this.addPotionEffect(CustomPotionEffect.SUPER_JUMP, CustomPotionEffect.GIANT_SLOWNESS);
		
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING);
		
		this.addBiomes(Biome.THE_VOID);
		
	}
}
