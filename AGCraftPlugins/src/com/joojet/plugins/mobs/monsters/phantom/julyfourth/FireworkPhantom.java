package com.joojet.plugins.mobs.monsters.phantom.julyfourth;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class FireworkPhantom extends MobEquipment
{
	public FireworkPhantom ()
	{
		super (MonsterType.FIREWORK_PHANTOM);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 1.0);
		this.addMobFlags(MobFlag.FIREWORK_DEATH);
		this.name = StringUtil.alternateTextColors("Shoot Me", TextPattern.WORD, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
	}
}
