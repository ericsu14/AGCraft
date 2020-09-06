package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

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
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.equipment.weapons.TrojanSword;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.scrolls.SummonFrolf;
import com.joojet.plugins.mobs.scrolls.SummonSpiritOfTroy;
import com.joojet.plugins.mobs.scrolls.SummonUSCArcher;
import com.joojet.plugins.mobs.scrolls.SummonUSCWarrior;

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
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 1.0);
		
		// Equipment
		this.weapon = new BruinSword (this.color);
		this.helmet = new ReinforcedDiamondHelmet (this.color);
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		
		// Mob Drops
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.10 , 2, 3),
				new MonsterDrop (new USCCreeperShield(), 1.00),
				new MonsterDrop (new SummonUSCArcher (), 1.00),
				new MonsterDrop (new SummonSpiritOfTroy(), 1.00),
				new MonsterDrop (new TrojanSword(ChatColor.GOLD), 1.00),
				new MonsterDrop (new SummonFrolf (), 0.10),
				new MonsterDrop (new SummonUSCWarrior(), 1.00),
				new MonsterDrop (Material.DIAMOND, 0.75, 1, 1),
				new MonsterDrop (Material.DIAMOND, 1.00, 1, 1));
		
		this.addPotionEffect(CustomPotionEffect.SUPER_JUMP, CustomPotionEffect.GIANT_SLOWNESS);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING);
		this.addBiomes(Biome.THE_VOID);
		
	}
}
