package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.DesertSandals;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WanderingHusk extends MobEquipment
{
	public WanderingHusk ()
	{
		this.addBiomes(Biome.THE_VOID);
		
		this.name = "Wandering Husk";
		this.color = ChatColor.GREEN;
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH,
				CustomPotionEffect.SPEED);
		
		this.boots = new DesertSandals (this.color);
	}
}
