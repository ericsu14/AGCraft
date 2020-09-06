package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofIronChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedIronHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedIronLeggings;
import com.joojet.plugins.mobs.equipment.offhand.HurtfulArrow;
import com.joojet.plugins.mobs.equipment.weapons.VeryPotentBow;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class HurtfulSkeleton extends MobEquipment  
{
	public HurtfulSkeleton ()
	{
		super (MonsterType.HURTFUL_SKELETON);
		this.name = "Hurtful Skeleton";
		this.color = ChatColor.BLUE;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new VeryPotentBow (this.color);
		// Offhand
		this.offhand = new HurtfulArrow (this.color);
		// Helmet
		this.helmet = new ReinforcedIronHelmet (this.color);
		// Chestplate
		this.chestplate = new BulletproofIronChestplate (this.color);
		// Leggings
		this.leggings = new ReinforcedIronLeggings (this.color);
		// Boots
		this.boots = new LightweightIronBoots (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 18.0);
	}
}
