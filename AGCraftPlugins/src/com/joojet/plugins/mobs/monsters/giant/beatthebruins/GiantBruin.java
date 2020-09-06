package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.FireworkDrop;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.ReinforcedDiamondHelmet;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.offhand.RightCrashSymbol;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.equipment.weapons.EternalSpiritOfTroy;
import com.joojet.plugins.mobs.equipment.weapons.LeftCrashSymbol;
import com.joojet.plugins.mobs.equipment.weapons.TrojanSword;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.scrolls.SummonFrolf;
import com.joojet.plugins.mobs.scrolls.SummonSpiritOfTroy;
import com.joojet.plugins.mobs.scrolls.SummonTrojanWarrior;
import com.joojet.plugins.mobs.scrolls.SummonUSCArcher;
import com.joojet.plugins.mobs.scrolls.SummonUSCWarrior;

public class GiantBruin extends UCLAFaction
{
	public GiantBruin ()
	{
		super (MonsterType.GIANT_BRUIN);
		this.color = ChatColor.AQUA;
		this.name = "Giant" + ChatColor.GOLD + " Bruin";
		this.setStat(MonsterStat.HEALTH, 250.0);
		this.setStat(MonsterStat.EXPERIENCE, 1000.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 150.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 2.0);
		
		// Equipment
		this.weapon = new BruinSword (this.color);
		this.helmet = new ReinforcedDiamondHelmet (this.color);
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		
		// Mob Drops
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.10 , 2, 3),
				new MonsterDrop (new USCCreeperShield(), 0.15),
				new MonsterDrop (new SummonUSCArcher (), 0.07),
				new MonsterDrop (new SummonSpiritOfTroy(), 0.07),
				new MonsterDrop (new TrojanSword(ChatColor.GOLD), 0.10),
				new MonsterDrop (new SummonFrolf (), 0.07),
				new MonsterDrop (new SummonUSCWarrior(), 0.20),
				new MonsterDrop (new SummonTrojanWarrior(), 0.05),
				new MonsterDrop (new EternalSpiritOfTroy(), 0.05),
				new MonsterDrop (new RightCrashSymbol (ChatColor.GOLD), 0.15),
				new MonsterDrop (new LeftCrashSymbol (ChatColor.GOLD), 0.15),
				new MonsterDrop (Material.DIAMOND, 1.00, 1, 2),
				new MonsterDrop (Material.DIAMOND, 1.00, 1, 2),
				new MonsterDrop (Material.EMERALD, 1.00, 4, 24),
				new FireworkDrop (0.75, 64, 64),
				new FireworkDrop (0.75, 64, 64),
				new FireworkDrop (0.75, 64, 64),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 0.75, 1, 2),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 0.35, 2, 3));
		
		this.addPotionEffect(CustomPotionEffect.SUPER_JUMP, CustomPotionEffect.GIANT_SLOWNESS);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING,
				MobFlag.FIREWORK_DEATH);
		this.addBiomes(Biome.THE_VOID);
		
	}
}
