package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

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
		this.health = 14;
		
		this.addBiomes(Biome.THE_VOID);
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Equipment
		this.weapon = new SpiritualTravesty (this.color);
		this.helmet = new DarkNetheriteHelmet (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.leggings = new DarkNetheriteLeggings (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
	}
}
