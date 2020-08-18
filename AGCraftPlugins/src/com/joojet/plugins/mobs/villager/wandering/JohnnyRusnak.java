package com.joojet.plugins.mobs.villager.wandering;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

public class JohnnyRusnak extends VillagerEquipment
{
	public JohnnyRusnak ()
	{
		super (MonsterType.JOHNNY_RUSNAK);
		this.name = "Johnny Rusnak";
		this.color = ChatColor.GOLD;
		this.addBiomes(Biome.THE_VOID);
		
		/** Trade 1: Golden Carrots (bundles of 16)
		 * 		- Price: 3 Emeralds
		 * 		- Max stock: 4 */
		ItemStack goldCarrot = new ItemStack (Material.GOLDEN_CARROT, 16);
		this.addRecipe(goldCarrot, Material.EMERALD, 3, 4);
	}
}
