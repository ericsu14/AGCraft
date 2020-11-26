package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCBandUniformTop;
import com.joojet.plugins.mobs.equipment.head.USCBandHead;
import com.joojet.plugins.mobs.equipment.leggings.USCBandUniformBottom;
import com.joojet.plugins.mobs.equipment.offhand.RightCrashSymbol;
import com.joojet.plugins.mobs.equipment.weapons.LeftCrashSymbol;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class SpiritOfTroy extends USCFaction 
{
	public SpiritOfTroy ()
	{
		super (MonsterType.SPIRIT_OF_TROY);
		this.addBiomes(Biome.THE_VOID);
		this.name = StringUtil.alternateTextColors("The Spirit of Troy", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD);
		this.color = ChatColor.RED;
		this.addPotionEffect(CustomPotionEffect.SPEED, CustomPotionEffect.WEAKNESS);
		this.setStat(MonsterStat.HEALTH, 12.0);
		this.helmet = new USCBandHead (this.color);
		this.chestplate = new USCBandUniformTop (this.color);
		this.leggings = new USCBandUniformBottom (this.color);
		this.boots = new USCSpikedBoots (this.color);
		this.weapon = new LeftCrashSymbol (this.color);
		this.offhand = new RightCrashSymbol (this.color);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		// TODO Auto-generated method stub
		
	}
}
