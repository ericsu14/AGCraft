package com.joojet.plugins.mobs.monsters.skeleton.hungergames;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCFootballTunic;
import com.joojet.plugins.mobs.equipment.head.USCTrojan;
import com.joojet.plugins.mobs.equipment.leggings.USCFootballTrousers;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.NerfDamageOutputSkill;
import com.joojet.plugins.mobs.skills.passive.USCTracerSkill;

public class HGUSCArcher extends USCFaction
{

	public HGUSCArcher() 
	{
		super(MonsterType.HG_USC_ARCHER);
		this.name = generateUSCDisplayName("Bowman");
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.GOLD;
		
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		this.setStat(MonsterStat.HEALTH, 4.0);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.COMMON);
		
		this.helmet = new USCTrojan (this.color);
		this.chestplate = new USCFootballTunic (this.color);
		this.leggings = new USCFootballTrousers (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new PotentBow (this.color);
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new NerfDamageOutputSkill (0.60));
		skills.add(new USCTracerSkill ());
	}

}
