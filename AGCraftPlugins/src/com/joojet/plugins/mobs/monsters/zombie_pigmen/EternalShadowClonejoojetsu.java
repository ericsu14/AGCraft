package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.EternalJoojHead;
import com.joojet.plugins.mobs.monsters.MobEquipment;


public class EternalShadowClonejoojetsu extends MobEquipment 
{
	public EternalShadowClonejoojetsu ()
	{
		super (MonsterType.ETERNAL_SHADOW_CLONE_JOOJETSU);
		this.name = StringUtil.alternateTextColors("Eternal Shadow Clone joojetsu", TextPattern.WORD, 
				ChatColor.WHITE, ChatColor.GOLD, ChatColor.RED, ChatColor.DARK_RED);
		this.color = ChatColor.DARK_RED;
		
		this.addBiomes(Biome.THE_VOID);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.HUNT_ON_SPAWN, 
				MobFlag.PERSISTENT_ATTACKER, MobFlag.SHOW_NAME,
				MobFlag.SPAWN_LIGHTNING);
		
		this.setStat(MonsterStat.HEALTH, 100.0);
		this.setStat(MonsterStat.EXPERIENCE, 500.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 75.0);
		
		this.helmet = new EternalJoojHead (this.color);
	}
}
