package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.BulletproofIronChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedIronHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedIronLeggings;
import com.joojet.plugins.mobs.equipment.weapons.VeryPotentBow;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class HurtfulSkeleton extends MobEquipment  
{
	public HurtfulSkeleton ()
	{
		this.name = "Skeleguard";
		this.color = ChatColor.BLUE;
		this.health = 16;
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new VeryPotentBow (this.color);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.BLACK);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 0), true);
		tippedArrow.setDisplayName(this.color + "Hurtful Arrow");
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new ReinforcedIronHelmet (this.color);
		// Chestplate
		this.chestplate = new BulletproofIronChestplate (this.color);
		// Leggings
		this.leggings = new ReinforcedIronLeggings (this.color);
		// Boots
		this.boots = new LightweightIronBoots (this.color);
	}
}
