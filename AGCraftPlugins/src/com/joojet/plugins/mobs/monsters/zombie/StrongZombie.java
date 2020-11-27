package com.joojet.plugins.mobs.monsters.zombie;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.BulletproofIronChestplate;
import com.joojet.plugins.mobs.equipment.weapons.SharpenedIronSword;
import com.joojet.plugins.mobs.monsters.factions.classifications.RareMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;

public class StrongZombie extends RareMob 
{
	public StrongZombie ()
	{
		super (MonsterType.STRONG_ZOMBIE);
		
		this.name = "Strong Zombie";
		this.color = ChatColor.BLUE;
		this.chestplate = new BulletproofIronChestplate (this.color);
		this.weapon = new SharpenedIronSword (this.color);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 12.0);
		this.setStat(MonsterStat.KNOCKBACK_RESISTANCE, 0.2);
		this.setStat(MonsterStat.BASE_SPEED, 0.25);
		this.setStat(MonsterStat.EXPERIENCE, 15.0);
	}
	
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AttackBuffSkill(1, 30, 10, 60, 8));
	}
}
