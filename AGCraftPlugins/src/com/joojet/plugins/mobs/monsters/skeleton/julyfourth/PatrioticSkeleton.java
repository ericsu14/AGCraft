package com.joojet.plugins.mobs.monsters.skeleton.julyfourth;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.PatrioticBlueBoots;
import com.joojet.plugins.mobs.equipment.chest.PatrioticRedJacket;
import com.joojet.plugins.mobs.equipment.head.USAHat;
import com.joojet.plugins.mobs.equipment.leggings.PatrioticWhiteLeggings;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class PatrioticSkeleton extends MobEquipment
{
	private FireworkTypes fwTypes;
	public PatrioticSkeleton ()
	{
		super (MonsterType.PATRIOTIC_SKELETON);
		this.fwTypes = new FireworkTypes ();
		this.name = this.americanizeText("Patriotic Skeleton");
		this.color = ChatColor.WHITE;
		this.health = 4.0;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH,
				CustomPotionEffect.SPEED);
		
		this.setDropRates(0.05f, 0.03f, 0.03f, 0.03f, 0.05f, 0.75f);
		
		this.helmet = new USAHat ();
		this.chestplate = new PatrioticRedJacket ();
		this.leggings = new PatrioticWhiteLeggings();
		this.boots = new PatrioticBlueBoots ();
		
		this.offhand = fwTypes.getRandomFirework(16, 2);
	}
}
