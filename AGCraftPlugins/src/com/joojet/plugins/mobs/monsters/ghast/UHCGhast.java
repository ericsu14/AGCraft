package com.joojet.plugins.mobs.monsters.ghast;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public class UHCGhast extends MobEquipment
{
	/** In UHC, ghasts have a 50% percent chance of dropping a golden ignot */
	public UHCGhast ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.weapon = new ItemStack (Material.GOLD_INGOT, 1);
		this.setDropRates(0.00f, 0.00f, 0.00f, 0.00f, 0.50f, 0.00f);
	}
}
