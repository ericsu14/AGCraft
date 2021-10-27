package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;


import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.offhand.BruinShield;
import com.joojet.plugins.mobs.equipment.weapons.BruinSword;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.NerfDamageOutputSkill;

public class UCLAJock extends UCLAFaction
{
	public UCLAJock ()
	{
		super (MonsterType.UCLA_JOCK);
		this.setStat(MonsterStat.HEALTH, 14.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 2.0);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
		
		this.name = "The " + generateUCLADisplayName("Jock");
		this.color = ChatColor.AQUA;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.weapon = new BruinSword (this.color);
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
		
		this.setStat(MonsterStat.EXPERIENCE, 24.0);
		this.setStat(MonsterStat.BASE_SPEED, 0.20);
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		super.loadCustomSkills(skills);
		// Nerfs damage output by 30% to prevent him from becoming too overwhelming
		skills.add(new NerfDamageOutputSkill (0.40));
	}

}
