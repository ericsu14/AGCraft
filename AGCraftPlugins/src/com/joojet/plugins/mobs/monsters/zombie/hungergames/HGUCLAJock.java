package com.joojet.plugins.mobs.monsters.zombie.hungergames;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
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
import com.joojet.plugins.mobs.skills.passive.UCLATracerSkill;

public class HGUCLAJock extends UCLAFaction 
{
	public HGUCLAJock() 
	{
		super(MonsterType.HG_UCLA_JOCK);
		this.setStat(MonsterStat.HEALTH, 6.0);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.COMMON);
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		
		this.name = "The " + generateUCLADisplayName("Jock");
		this.color = ChatColor.AQUA;
		
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.weapon = new BruinSword (this.color);
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.offhand = new BruinShield ();
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new NerfDamageOutputSkill (0.85));
		skills.add(new UCLATracerSkill ());
	}

}
