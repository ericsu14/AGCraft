package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.BulletproofIronChestplate;
import com.joojet.plugins.mobs.monsters.factions.classifications.UncommonMob;

public class StrongZombie extends UncommonMob 
{
	public StrongZombie ()
	{
		super (MonsterType.STRONG_ZOMBIE);
		
		this.name = "Strong Zombie";
		this.color = ChatColor.GREEN;
		this.chestplate = new BulletproofIronChestplate (this.color);
		this.weapon = new ItemStack (Material.IRON_SWORD, 1);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 6.0);
		this.setStat(MonsterStat.HEALTH, 8.0);
		this.setStat(MonsterStat.KNOCKBACK_RESISTANCE, 0.2);
		this.setStat(MonsterStat.BASE_SPEED, 0.2);
	}
}
