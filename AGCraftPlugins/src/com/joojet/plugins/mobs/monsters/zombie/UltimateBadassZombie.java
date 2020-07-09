package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualTravesty;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UltimateBadassZombie extends MobEquipment
{
	public UltimateBadassZombie ()
	{
		this.name = "Shadow Clone joojetsu";
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new SpiritualTravesty (this.color);
		
		// Helmet
		this.helmet = new DarkNetheriteHelmet (this.color);
		
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
		// Leggings
		this.leggings = new DarkNetheriteLeggings (this.color);
		
		// Boots
		this.boots = new LightweightNetheriteBoots (this.color);
	}
}
