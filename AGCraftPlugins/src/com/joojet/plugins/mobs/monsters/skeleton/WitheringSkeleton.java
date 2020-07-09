package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;

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
		this.health = 30;
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
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
