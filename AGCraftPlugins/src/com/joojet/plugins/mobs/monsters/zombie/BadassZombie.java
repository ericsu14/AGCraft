package com.joojet.plugins.mobs.monsters.zombie;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedDiamondChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedIronHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedIronLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SharpenedIronSword;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;

public class BadassZombie extends EpicMob implements CustomSkillUser
{
	public BadassZombie ()
	{
		super (MonsterType.BADASS_ZOMBIE);
		this.name = "Badass Zombie";
		this.color = ChatColor.LIGHT_PURPLE;
		this.setStat(MonsterStat.HEALTH, 16.0);
		
		this.addBiomes(Biome.THE_VOID);
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		
		// Weapon
		this.weapon = new SharpenedIronSword (this.color);
		// Helmet
		this.helmet = new ReinforcedIronHelmet (this.color);
		// Chestplate
		this.chestplate = new ReinforcedDiamondChestplate (this.color);
		// Leggings
		this.leggings = new ReinforcedIronLeggings (this.color);
		// Boots
		this.boots = new LightweightIronBoots (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 25.0);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new AttackBuffSkill(1, 45, 15, 60, 8));
	}
}
