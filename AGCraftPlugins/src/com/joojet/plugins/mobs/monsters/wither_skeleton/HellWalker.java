package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.drops.SummoningScrollDrop;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.HellwakerHead;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.offhand.HellwalkerDagger;
import com.joojet.plugins.mobs.equipment.weapons.HellwalkerBlade;
import com.joojet.plugins.mobs.monsters.factions.classifications.MythicMob;
import com.joojet.plugins.music.enums.MusicType;

public class HellWalker extends MythicMob 
{
	public HellWalker ()
	{
		super (MonsterType.HELL_WALKER);
		this.name = StringUtil.alternateTextColors("The Hell Walker", TextPattern.WORD, 
				ChatColor.DARK_GRAY, ChatColor.DARK_RED);
		this.color = ChatColor.DARK_RED;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.helmet = new HellwakerHead(this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.leggings = new DarkNetheriteLeggings (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		this.weapon = new HellwalkerBlade (this.color);
		this.offhand = new HellwalkerDagger (this.color);
		
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER, MobFlag.HUNT_ON_SPAWN, 
				MobFlag.SPAWN_LIGHTNING, MobFlag.SHOW_NAME);
		this.setStat(MonsterStat.EXPERIENCE, 60.0);
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 12.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 75.0);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.35, 1, 3),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 1.00, 1, 1),
				new SummoningScrollDrop (SummonTypes.HELL_WALKER, 0.10));
		
		this.bossTheme = MusicType.HAIKYUU;
		
	}
}
