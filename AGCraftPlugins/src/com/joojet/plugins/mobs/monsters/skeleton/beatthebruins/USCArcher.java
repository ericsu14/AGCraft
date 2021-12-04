package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import java.util.List;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCFootballTunic;
import com.joojet.plugins.mobs.equipment.head.USCTrojan;
import com.joojet.plugins.mobs.equipment.leggings.USCFootballTrousers;
import com.joojet.plugins.mobs.equipment.weapons.FightOn;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.DisableFriendlyFireSkill;

public class USCArcher extends USCFaction
{
	public USCArcher ()
	{
		super (MonsterType.USC_ARCHER);
		this.name = generateUSCDisplayName("Bowman");
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 6.0);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.20);
		this.setStat(MonsterStat.BASE_SPEED, 0.22);
		this.helmet = new USCTrojan (this.color);
		this.chestplate = new USCFootballTunic (this.color);
		this.leggings = new USCFootballTrousers (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new FightOn (this.color);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills)
	{
		super.loadCustomSkills(skills);
		skills.add(new DisableFriendlyFireSkill());
	}
}
