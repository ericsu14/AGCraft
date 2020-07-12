package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.offhand.WitheringArrow;
import com.joojet.plugins.mobs.equipment.weapons.AngelOfDeath;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WitheringSkeleton extends MobEquipment 
{
	public WitheringSkeleton ()
	{
		this.name = "Soul Eater";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.setDropRates(0.00f, 0.00f, 0.00f, 0.00f, 0.075f, 0.075f);
		
		// Weapon
		this.weapon = new AngelOfDeath (this.color);
		// Offhand
		this.offhand = new WitheringArrow (this.color);
		// Helmet
		this.helmet = new DarkNetheriteHelmet (this.color);
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
	}
}
