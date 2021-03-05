package com.joojet.plugins.mobs.monsters.skeleton;


import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightChainmailBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofChainmailChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedChainmailHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedChainmailLeggings;
import com.joojet.plugins.mobs.equipment.offhand.WeakeningArrow;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.monsters.factions.OverworldMob;

public class UncommonSkeleton extends OverworldMob
{
	public UncommonSkeleton ()
	{
		super (MonsterType.UNCOMMON_SKELETON);
		this.name = "Annoying Skeleton";
		this.color = ChatColor.GREEN;
		this.setStat(MonsterStat.HEALTH, 12.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.40);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.05);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 3.0);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.UNCOMMON);
		
		this.weapon = new PotentBow (this.color);
		this.offhand = new WeakeningArrow (this.color);
		this.helmet = new ReinforcedChainmailHelmet (this.color);
		this.chestplate = new BulletproofChainmailChestplate (this.color);
		this.leggings = new ReinforcedChainmailLeggings (this.color);
		this.boots = new LightweightChainmailBoots (this.color);
	}
}
