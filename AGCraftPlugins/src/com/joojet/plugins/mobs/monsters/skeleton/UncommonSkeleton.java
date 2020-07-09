package com.joojet.plugins.mobs.monsters.skeleton;


import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.equipment.boots.LightweightChainmailBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofChainmailChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedChainmailHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedChainmailLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PotentBow;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UncommonSkeleton extends MobEquipment
{
	public UncommonSkeleton ()
	{
		this.name = "Annoying Skeleton";
		this.color = ChatColor.GREEN;
		this.health = 16;
		
		// Weapon
		this.weapon = new PotentBow (this.color);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.GRAY);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.WEAKNESS, 70, 0), true);
		tippedArrow.setDisplayName(this.color + "Weakening Arrow");
		this.addLoreToItemMeta(tippedArrow, "Weakens the enemy for a short amount of time.");
		this.offhand.setItemMeta(tippedArrow);
		
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
