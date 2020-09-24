package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinBanditHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.PoisonousArrow;
import com.joojet.plugins.mobs.equipment.weapons.TheTrojanDestroyer;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.monsters.polar_bear.beatthebruins.TheBruinBear;

public class UCLABearTamer extends UCLAFaction 
{
	public UCLABearTamer ()
	{
		super (MonsterType.UCLA_BEAR_TAMER);
		this.name = "The " + generateUCLADisplayName("Bear Tamer");
		this.setStat(MonsterStat.EXPERIENCE, 50.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 100.0);
		this.setStat(MonsterStat.Y_LIMIT, 55);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 12.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.40);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.20);
		
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.addMobFlags(MobFlag.SPAWN_LIGHTNING, MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME,
				MobFlag.BOSS_BAR);
		this.color = ChatColor.AQUA;
		this.helmet = new BruinBanditHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.weapon = new TheTrojanDestroyer ();
		this.offhand = new PoisonousArrow (ChatColor.GREEN);
		this.mount = new MountedMob (EntityType.POLAR_BEAR, new TheBruinBear());
		
		this.addMonsterDrops(new MonsterDrop (new TheTrojanDestroyer(), 0.05));
	}
}
