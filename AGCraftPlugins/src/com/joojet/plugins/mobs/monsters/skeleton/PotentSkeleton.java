package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofIronChestplate;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedChainmailLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.monsters.factions.OverworldMob;

public class PotentSkeleton extends OverworldMob 
{
	public PotentSkeleton ()
	{
		super (MonsterType.POTENT_SKELETON);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.RARE);
		this.name = "Potent Skeleton";
		this.color = ChatColor.BLUE;
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.30);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.10);
		this.weapon = new PotentBow (this.color);
		this.chestplate = new BulletproofIronChestplate (this.color);
		this.leggings = new ReinforcedChainmailLeggings (this.color);
		this.boots = new LightweightIronBoots (this.color);
		this.setStat(MonsterStat.BASE_SPEED, 0.20);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.EXPERIENCE, 15.0);
	}
}
