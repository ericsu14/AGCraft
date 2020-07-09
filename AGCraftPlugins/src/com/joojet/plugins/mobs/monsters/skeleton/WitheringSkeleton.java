package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.weapons.AngelOfDeath;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WitheringSkeleton extends MobEquipment 
{
	public WitheringSkeleton ()
	{
		this.name = "Soul Eater";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 30;
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new AngelOfDeath (this.color);
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.BLACK);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.WITHER, 140, 1), true);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 1), true);
		tippedArrow.setDisplayName(this.color + "Withering Arrow");
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new DarkNetheriteHelmet (this.color);
		
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
	}
}
