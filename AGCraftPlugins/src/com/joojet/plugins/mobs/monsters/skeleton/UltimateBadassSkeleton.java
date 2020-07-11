package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.AGSpotted;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.offhand.ThanosArrow;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UltimateBadassSkeleton extends MobEquipment
{
	public UltimateBadassSkeleton ()
	{
		this.name = "#agspotted";
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new SpiritualFantasy (this.color);
		
		// Offhand
		this.offhand = new ThanosArrow (this.color);
		
		// Helmet
		this.helmet = new AGSpotted (this.color);
		
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
		// Leggings
		this.leggings = new DarkNetheriteLeggings (this.color);
		
		// Boots
		this.boots = new LightweightNetheriteBoots (this.color);
		
	}
}
