package com.joojet.plugins.mobs.monsters.pillager;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class PatrioticPillager extends MobEquipment
{
	private FireworkTypes fwTypes;
	public PatrioticPillager ()
	{
		fwTypes = new FireworkTypes();
		this.name = this.americanizeText("Patriotic Pillager");
		this.health = 16;
		this.offhand = fwTypes.getRandomFirework(16, 2);
		this.weapon = new FireworkLauncher (ChatColor.GOLD);
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.85f);
	}
}
