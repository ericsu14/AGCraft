package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofIronChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedIronHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedIronLeggings;
import com.joojet.plugins.mobs.equipment.offhand.HurtfulArrow;
import com.joojet.plugins.mobs.equipment.weapons.VeryPotentBow;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;

public class HurtfulSkeleton extends EpicMob  
{
	public HurtfulSkeleton ()
	{
		super (MonsterType.HURTFUL_SKELETON);
		this.name = "Hurtful Skeleton";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.weapon = new VeryPotentBow (this.color);
		this.offhand = new HurtfulArrow (this.color);
		this.helmet = new ReinforcedIronHelmet (this.color);
		this.chestplate = new BulletproofIronChestplate (this.color);
		this.leggings = new ReinforcedIronLeggings (this.color);
		this.boots = new LightweightIronBoots (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 24.0);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.75);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.15);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 6.0);
	}
}
