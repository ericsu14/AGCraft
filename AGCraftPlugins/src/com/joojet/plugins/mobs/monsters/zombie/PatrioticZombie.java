package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.PatrioticBlueBoots;
import com.joojet.plugins.mobs.equipment.chest.PatrioticRedJacket;
import com.joojet.plugins.mobs.equipment.head.USAHat;
import com.joojet.plugins.mobs.equipment.leggings.PatrioticWhiteLeggings;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class PatrioticZombie extends MobEquipment
{	
	private FireworkTypes fwTypes;
	public PatrioticZombie ()
	{
		this.fwTypes = new FireworkTypes ();
		this.name = this.americanizeText("Patriotic Zombie");
		this.color = ChatColor.WHITE;
		this.health = 4.0;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.setDropRates(0.05f, 0.03f, 0.03f, 0.03f, 0.05f, 0.75f);
		
		this.helmet = new USAHat ();
		this.chestplate = new PatrioticRedJacket ();
		this.leggings = new PatrioticWhiteLeggings();
		this.boots = new PatrioticBlueBoots ();
		
		this.offhand = fwTypes.getRandomFirework(16, 2);
	}

}
