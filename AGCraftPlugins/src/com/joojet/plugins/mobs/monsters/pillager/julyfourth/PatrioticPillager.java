package com.joojet.plugins.mobs.monsters.pillager.julyfourth;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class PatrioticPillager extends MobEquipment
{
	private FireworkTypes fwTypes;
	public PatrioticPillager ()
	{
		super (MonsterType.PATRIOTIC_PILLAGER);
		this.addBiomes(Biome.THE_VOID);
		
		fwTypes = new FireworkTypes();
		this.name = this.americanizeText("Patriotic Pillager");
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.offhand = fwTypes.getRandomFirework(16, 2);
		this.weapon = new FireworkLauncher (ChatColor.GOLD);
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.85f);
	}
}
