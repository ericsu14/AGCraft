package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
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
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.HurricaneSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.summon.SummonBruinSkill;
import com.joojet.plugins.music.enums.MusicType;

public class UCLABearTamer extends UCLAFaction 
{
	public UCLABearTamer ()
	{
		super (MonsterType.UCLA_BEAR_TAMER);
		this.name = "The " + generateUCLADisplayName("Bear Tamer");
		this.setStat(MonsterStat.EXPERIENCE, 50.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 100.0);
		this.setStat(MonsterStat.Y_LIMIT, 55);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 10.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.30);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.50);
		this.setStat(MonsterStat.HEALTH, 40.0);
		
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
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.LEGENDARY);
		
		this.addMonsterDrops(new MonsterDrop (new TheTrojanDestroyer(), 0.05));
		this.bossTheme = MusicType.HAIKYUU;
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		super.loadCustomSkills(skills);
		skills.add(new ThundagaSkill (24, 20, Integer.MAX_VALUE, 3, 3.0F, 6, 80, 0.70));
		skills.add(new HurricaneSkill (24, 20, Integer.MAX_VALUE, 16, 8, 0.5));
		skills.add(new SummonBruinSkill (24, 90, 2, 4, 6));
	}
}
