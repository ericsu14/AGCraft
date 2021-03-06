package com.joojet.plugins.mobs.monsters.piglin;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.RoyalGoldChestplate;
import com.joojet.plugins.mobs.equipment.head.PiglinSoldierHat;
import com.joojet.plugins.mobs.equipment.leggings.RoyalGoldLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PiglinSword;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;

public class PiglinSoldier extends EpicMob implements CustomSkillUser
{
	public PiglinSoldier ()
	{
		super (MonsterType.PIGLIN_SOLDIER);
		this.name = "Piglin Soldier";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new PiglinSoldierHat (this.color);
		this.chestplate = new RoyalGoldChestplate (this.color);
		this.leggings = new RoyalGoldLeggings (this.color);
		this.weapon = new PiglinSword (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 25.0);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new AttackBuffSkill(0, 60, 20, 60, 8));
	}
}
