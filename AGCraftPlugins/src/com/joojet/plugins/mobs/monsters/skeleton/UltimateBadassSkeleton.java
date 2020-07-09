package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.AGSpotted;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UltimateBadassSkeleton extends MobEquipment
{
	public UltimateBadassSkeleton ()
	{
		this.name = "#agspotted";
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new SpiritualFantasy (this.color);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.fromRGB(75, 0, 130));
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.WITHER, 70, 2), true);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 1), true);
		tippedArrow.setDisplayName(this.color + "Thanos Arrow");
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new AGSpotted (this.color);
		
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
		// Leggings
		this.leggings = new DarkNetheriteLeggings (this.color);
		
		// Boots
		this.boots = new LightweightNetheriteBoots (this.color);
		
	}
}
