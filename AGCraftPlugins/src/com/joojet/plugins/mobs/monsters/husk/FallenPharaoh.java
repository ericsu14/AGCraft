package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.ChatColor;
import com.joojet.plugins.mobs.equipment.boots.RoyalGoldBoots;
import com.joojet.plugins.mobs.equipment.chest.RoyalGoldChestplate;
import com.joojet.plugins.mobs.equipment.head.PharaohHead;
import com.joojet.plugins.mobs.equipment.leggings.RoyalGoldLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PharaohStaff;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class FallenPharaoh extends MobEquipment
{
	public FallenPharaoh ()
	{
		this.name = "Fallen Pharaoh";
		this.color = ChatColor.LIGHT_PURPLE;

		this.helmet = new PharaohHead (this.color);
		this.chestplate = new RoyalGoldChestplate (this.color);
		this.leggings = new RoyalGoldLeggings (this.color);
		this.boots = new RoyalGoldBoots (this.color);
		this.weapon = new PharaohStaff (this.color);
	}
}
