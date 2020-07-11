package com.joojet.plugins.mobs.monsters.skeleton;


import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.equipment.boots.LightweightChainmailBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofChainmailChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedChainmailHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedChainmailLeggings;
import com.joojet.plugins.mobs.equipment.offhand.WeakeningArrow;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UncommonSkeleton extends MobEquipment
{
	public UncommonSkeleton ()
	{
		this.name = "Annoying Skeleton";
		this.color = ChatColor.GREEN;
		this.health = 16;
		
		this.addBiomes(Biome.THE_VOID);
		
		// Weapon
		this.weapon = new PotentBow (this.color);
		
		// Offhand
		this.offhand = new WeakeningArrow (this.color);
		
		// Helmet
		this.helmet = new ReinforcedChainmailHelmet (this.color);
		
		// Chestplate
		this.chestplate = new BulletproofChainmailChestplate (this.color);
		
		// Leggings
		this.leggings = new ReinforcedChainmailLeggings (this.color);
		
		// Boots
		this.boots = new LightweightChainmailBoots (this.color);
	}
}
