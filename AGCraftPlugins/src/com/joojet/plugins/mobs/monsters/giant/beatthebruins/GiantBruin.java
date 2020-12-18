package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.*;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.ReinforcedDiamondHelmet;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.offhand.RightCrashSymbol;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.equipment.weapons.EternalSpiritOfTroy;
import com.joojet.plugins.mobs.equipment.weapons.EternalTrojanSword;
import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.equipment.weapons.LeftCrashSymbol;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.music.enums.MusicType;

public class GiantBruin extends UCLAFaction
{
	public GiantBruin ()
	{
		super (MonsterType.GIANT_BRUIN);
		this.color = ChatColor.AQUA;
		this.name = "Giant" + ChatColor.GOLD + " Bruin";
		this.setStat(MonsterStat.HEALTH, 250.0);
		this.setStat(MonsterStat.EXPERIENCE, 1200.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 150.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 6.0);
		this.setStat(MonsterStat.KNOCKBACK_RESISTANCE, 4.50);
		
		// Equipment
		this.weapon = new BruinSword (this.color);
		this.helmet = new ReinforcedDiamondHelmet (this.color);
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		
		// Mob Drops
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.10 , 2, 3),
				new MonsterDrop (new USCCreeperShield(), 0.07),
				new SummoningScrollDrop (SummonTypes.USC_ARCHER, 0.07),
				new SummoningScrollDrop (SummonTypes.SPIRIT_OF_TROY, 0.07),
				new SummoningScrollDrop (SummonTypes.ETERNAL_TROJAN_ARCHER, 0.03),
				new SummoningScrollDrop (SummonTypes.FROLF, 0.07),
				new SummoningScrollDrop (SummonTypes.JOHNNY_RUSNAK, 0.07),
				new SummoningScrollDrop (SummonTypes.SPIRIT_OF_TROY, 0.07),
				new SummoningScrollDrop (SummonTypes.USC_WARRIOR, 0.07),
				new SummoningScrollDrop (SummonTypes.TROJAN_WARRIOR, 0.03),
				new MonsterDrop (new EternalTrojanSword(), 0.07),
				new MonsterDrop (new EternalSpiritOfTroy(), 0.03),
				new MonsterDrop (new RightCrashSymbol (ChatColor.GOLD), 0.05),
				new MonsterDrop (new LeftCrashSymbol (ChatColor.GOLD), 0.05),
				new MonsterDrop (new FireworkLauncher (ChatColor.GOLD), 0.07),
				new MonsterDrop (Material.DIAMOND, 0.15, 1, 2),
				new MonsterDrop (Material.DIAMOND, 0.07, 1, 2),
				new MonsterDrop (Material.EMERALD, 0.10, 4, 24),
				new MonsterDrop (Material.GOLDEN_CARROT, 0.50, 16, 32),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 1.00, 1, 1),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.07, 2, 3),
				new FireworkDrop (0.75, 8, 32),
				new FireworkDrop (0.50, 16, 32),
				new FireworkDrop (0.25, 32, 32),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 0.25, 5, 7),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 0.10, 2, 3));
		
		this.addPotionEffect(CustomPotionEffect.SUPER_JUMP, CustomPotionEffect.GIANT_SLOWNESS);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING,
				MobFlag.FIREWORK_DEATH);
		this.addBiomes(Biome.THE_VOID);
		this.bossTheme = MusicType.HAIKYUU;
		
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AttackBuffSkill(0, 60, 50, 90, 8));
		skills.add(new ResistanceBuffSkill (1, 60, 50, 90, 8));
		skills.add(new ThundagaSkill (40, 20, Integer.MAX_VALUE, 10, 4.0f, 4, 80, 0.50));
	}
}
