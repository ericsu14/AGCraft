package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.offhand.PigmanDagger;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;


public class AkimboPigman extends LegendaryMob 
{
	public AkimboPigman ()
	{
		super (MonsterType.AKIMBO_PIGMAN);
		this.name = "Akimbo Pigman";
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new DarkNetheriteHelmet (this.color);
		this.weapon = new PigmanSword (this.color);
		this.offhand = new PigmanDagger (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 40.0);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AttackBuffSkill(1, 30));
		skills.add(new ResistanceBuffSkill (1, 30));
	}
}
