package com.joojet.plugins.mobs.monsters.piglin;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.PiglinCaptainHead;
import com.joojet.plugins.mobs.equipment.weapons.PiglinAxe;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.summon.SummonPiglinArmySkill;
import com.joojet.plugins.music.enums.MusicType;

public class PiglinCaptain extends LegendaryMob 
{
	public PiglinCaptain ()
	{
		super (MonsterType.PIGLIN_CAPTAIN);
		this.name = "Piglin Captain";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.setStat(MonsterStat.BASE_ARMOR, 8.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 6.0);
		
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new PiglinCaptainHead (this.color);
		this.weapon = new PiglinAxe (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
		this.addPotionEffect(CustomPotionEffect.REGEN);
		
		this.setStat(MonsterStat.EXPERIENCE, 45.0);
		this.bossTheme = MusicType.KUZE_THEME;
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AttackBuffSkill(1, 60, 20, 60, 8));
		skills.add(new ResistanceBuffSkill (1, 60, 20, 60, 8));
		skills.add(new SummonPiglinArmySkill (12, Integer.MAX_VALUE, 1, 8, 4));
	}
}
