package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.allies.horse.beatthebruins.TheTraveler;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCBandUniformTop;
import com.joojet.plugins.mobs.equipment.head.USCBandHead;
import com.joojet.plugins.mobs.equipment.leggings.USCBandUniformBottom;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.EternalTrojanSword;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class TrojanWarrior extends USCFaction 
{
	public TrojanWarrior ()
	{
		super (MonsterType.TROJAN_WARRIOR);
		this.addBiomes(Biome.THE_VOID);
		
		// Allows the Trojan Warrior to hunt creepers
		this.ignoreList.remove(EntityType.CREEPER);
		this.addTargetsToHitList(EntityType.CREEPER);
		
		this.name = StringUtil.alternateTextColors("The Trojan Warrior", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD);
		
		this.color = ChatColor.GOLD;
		this.mount = new MountedMob (EntityType.HORSE, new TheTraveler());
		
		this.weapon = new EternalTrojanSword ();
		this.offhand = new USCCreeperShield ();
		this.helmet = new USCBandHead (this.color);
		this.chestplate = new USCBandUniformTop (this.color);
		this.leggings = new USCBandUniformBottom (this.color);
		this.boots = new USCSpikedBoots (this.color);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.setStat(MonsterStat.HEALTH, 40.0);
		
		this.addMobFlags(MobFlag.SHOW_NAME,
				MobFlag.DISABLE_SUFFOCATION_DAMAGE);
	}
}
