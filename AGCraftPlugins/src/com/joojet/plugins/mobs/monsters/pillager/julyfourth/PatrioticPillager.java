package com.joojet.plugins.mobs.monsters.pillager.julyfourth;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.monsters.factions.classifications.UncommonMob;

public class PatrioticPillager extends UncommonMob
{
	public PatrioticPillager ()
	{
		super (MonsterType.PATRIOTIC_PILLAGER);
		this.addBiomes(Biome.THE_VOID);
		
		this.addMobFlags(MobFlag.RANDOM_FIREWORK_ON_OFFHAND);
		
		this.name = StringUtil.alternateTextColors("Patriotic Pillager", 
				TextPattern.WORD, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.weapon = new FireworkLauncher (ChatColor.GOLD);
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.85f);
	}
}
