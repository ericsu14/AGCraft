package com.joojet.plugins.mobs.monsters.skeleton.hungergames;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.BruinFootballBoots;
import com.joojet.plugins.mobs.equipment.chest.BruinTunic;
import com.joojet.plugins.mobs.equipment.head.BruinHead;
import com.joojet.plugins.mobs.equipment.leggings.BruinLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.NerfDamageOutputSkill;
import com.joojet.plugins.mobs.skills.passive.UCLATracerSkill;

public class HGUCLAArcher extends UCLAFaction 
{

	public HGUCLAArcher() 
	{
		super(MonsterType.HG_UCLA_ARCHER);
		this.name = "The " + generateUCLADisplayName("Archer");
		this.addBiomes(Biome.THE_VOID);

		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		this.setStat(MonsterStat.HEALTH, 4.0);
		this.setStat(MonsterStat.MONSTER_CLASSIFIER, MonsterClassifier.COMMON);
		
		this.color = ChatColor.AQUA;
		this.helmet = new BruinHead ();
		this.chestplate = new BruinTunic (this.color);
		this.leggings = new BruinLeggings (this.color);
		this.boots = new BruinFootballBoots (this.color);
		this.weapon = new PotentBow (this.color);
		
		this.addMonsterDrops(
			new MonsterDrop (Material.BOW, 0.075, 1, 1)
		);
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new NerfDamageOutputSkill (0.60));
		skills.add(new UCLATracerSkill ());
	}

}
