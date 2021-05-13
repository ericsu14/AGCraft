package com.joojet.plugins.mobs.monsters.spider;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.weapons.FireVenomFang;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.visual.FireAura;

public class EnragedSpider extends EpicMob implements CustomSkillUser
{
	public EnragedSpider ()
	{
		super (MonsterType.ENRAGED_SPIDER);
		this.name = "One Enraged Firey Boi";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f);
		
		// Custom potion effects
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		
		// Weapon
		this.weapon = new FireVenomFang (this.color);
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 22.0);
	}
	
	/** Allows the enraged spider to have a fire aura effect. It is completely visual, but should distinguish
	 *  this spider from other spiders. */
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new FireAura ());
	}
}
