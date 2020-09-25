package com.joojet.plugins.mobs.monsters.skeleton;


import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightChainmailBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofChainmailChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedChainmailHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedChainmailLeggings;
import com.joojet.plugins.mobs.equipment.offhand.WeakeningArrow;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class UncommonSkeleton extends MobEquipment
{
	public UncommonSkeleton ()
	{
		super (MonsterType.UNCOMMON_SKELETON);
		this.name = "Annoying Skeleton";
		this.color = ChatColor.GREEN;
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.10);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 1.00);
		
		this.addBiomes(Biome.THE_VOID);
		
		this.weapon = new PotentBow (this.color);
		this.offhand = new WeakeningArrow (this.color);
		this.helmet = new ReinforcedChainmailHelmet (this.color);
		this.chestplate = new BulletproofChainmailChestplate (this.color);
		this.leggings = new ReinforcedChainmailLeggings (this.color);
		this.boots = new LightweightChainmailBoots (this.color);
	}
}
