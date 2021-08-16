package com.joojet.plugins.mobs.monsters.polar_bear.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.drops.*;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.UCLATracerSkill;
import com.joojet.plugins.music.enums.MusicType;

public class TheBruinBear extends UCLAFaction
{
	public TheBruinBear ()
	{
		super (MonsterType.BRUIN_BEAR);
		this.color = ChatColor.AQUA;
		this.name = "The" + ChatColor.GOLD + " Bruin" + this.color + " Bear";
		this.setStat(MonsterStat.HEALTH, 60.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 20.0);
		this.setStat(MonsterStat.BASE_ARMOR, 14.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 8.0);
		this.addTargetsToHitList(EntityType.CREEPER, EntityType.IRON_GOLEM);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.SHOW_NAME, MobFlag.PERSISTENT_ATTACKER, MobFlag.DISABLE_PERSISTENCE);
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.STRENGTH_II, CustomPotionEffect.JUMP_BOOST,
				CustomPotionEffect.SPEED, CustomPotionEffect.RESISTANCE, CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.REGEN);
		// Drops
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.10, 1, 3),
				new MonsterDrop (new USCCreeperShield (), 0.10),
				new MonsterDrop (new BruinShield (), 0.10),
				new SummoningScrollDrop (SummonTypes.USC_ARCHER, 0.05),
				new SummoningScrollDrop (SummonTypes.SPIRIT_OF_TROY, 0.05),
				new SummoningScrollDrop (SummonTypes.USC_WARRIOR, 0.05),
				new SummoningScrollDrop (SummonTypes.TROJAN_WARRIOR, 0.05),
				new SummoningScrollDrop (SummonTypes.ETERNAL_TROJAN_ARCHER, 0.05),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.15, 1, 1));
		this.setStat(MonsterStat.EXPERIENCE, 200.0);
		this.ignoreList.clear();
		this.bossTheme = MusicType.BLINDED_BY_LIGHT;
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new UCLATracerSkill ());	
	}
}
