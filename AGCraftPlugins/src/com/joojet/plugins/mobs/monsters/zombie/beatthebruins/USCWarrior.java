package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCFootballTunic;
import com.joojet.plugins.mobs.equipment.head.USCTrojan;
import com.joojet.plugins.mobs.equipment.leggings.USCFootballTrousers;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.TrojanSword;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.NerfDamageOutputSkill;

public class USCWarrior extends USCFaction
{
	public USCWarrior ()
	{
		super (MonsterType.USC_WARRIOR);
		this.setStat(MonsterStat.HEALTH, 14.0);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
		
		this.name = generateUSCDisplayName("Trojan");
		
		this.color = ChatColor.GOLD;
		this.addBiomes(Biome.THE_VOID);
		
		this.helmet = new USCTrojan (this.color);
		this.chestplate = new USCFootballTunic (this.color);
		this.leggings = new USCFootballTrousers (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new TrojanSword (this.color);
		this.offhand = new USCCreeperShield ();
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		super.loadCustomSkills(skills);
		// Nerfs damage output by 40% to balance his damage output against the UCLA jock
		skills.add(new NerfDamageOutputSkill (0.40));
	}
}
