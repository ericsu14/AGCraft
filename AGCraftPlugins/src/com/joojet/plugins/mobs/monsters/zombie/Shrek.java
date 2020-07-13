package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.equipment.boots.OgreBoots;
import com.joojet.plugins.mobs.equipment.chest.OgreTunic;
import com.joojet.plugins.mobs.equipment.head.ShrekHat;
import com.joojet.plugins.mobs.equipment.leggings.OgreLeggings;
import com.joojet.plugins.mobs.equipment.weapons.OgreAxe;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Shrek extends MobEquipment
{
	public Shrek ()
	{
		this.color = ChatColor.DARK_GREEN;
		this.name = "Shrek";
		this.addBiomes(Biome.SWAMP, Biome.SWAMP_HILLS);
		
		this.helmet = new ShrekHat (this.color);
		this.chestplate = new OgreTunic (this.color);
		this.leggings = new OgreLeggings (this.color);
		this.boots = new OgreBoots (this.color);
		this.weapon = new OgreAxe (this.color);
	}
}
