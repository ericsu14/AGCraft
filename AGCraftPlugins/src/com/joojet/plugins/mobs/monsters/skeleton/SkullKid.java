package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.equipment.boots.SkullKidBoots;
import com.joojet.plugins.mobs.equipment.chest.SkullKidChest;
import com.joojet.plugins.mobs.equipment.head.SkullKidHelmet;
import com.joojet.plugins.mobs.equipment.leggings.SkullKidPants;
import com.joojet.plugins.mobs.equipment.weapons.ATerribleFate;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class SkullKid extends MobEquipment 
{
	public SkullKid ()
	{
		this.name = "??????";
		this.addBiomes(
				Biome.WOODED_MOUNTAINS, 
				Biome.SHATTERED_SAVANNA_PLATEAU,
				Biome.SHATTERED_SAVANNA,
				Biome.GRAVELLY_MOUNTAINS,
				Biome.SNOWY_TAIGA_MOUNTAINS);
		
		this.health = 40;
		this.color = ChatColor.DARK_RED;
		this.showName = true;
		
		this.helmet = new SkullKidHelmet (this.color);
		this.chestplate = new SkullKidChest (this.color);
		this.leggings = new SkullKidPants (this.color);
		this.boots = new SkullKidBoots (this.color);
		this.weapon = new ATerribleFate (this.color);
	}
}
