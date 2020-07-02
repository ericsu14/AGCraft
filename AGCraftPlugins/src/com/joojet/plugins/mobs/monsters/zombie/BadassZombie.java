package com.joojet.plugins.mobs.monsters.zombie;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class BadassZombie extends MobEquipment
{
	public BadassZombie ()
	{
		this.name = "Badass Zombie";
		this.color = ChatColor.LIGHT_PURPLE;
		
		// Custom potion effects
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		
		// Weapon
		this.weapon = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		
		this.addAttackAttributes(weaponMeta, EquipmentSlot.HAND, 7.0, 2.4);
		
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		ArrayList <String> weaponLore = new ArrayList <String> ();
		weaponLore.add(this.color + "These zombies were formally");
		weaponLore.add(this.color + "avengers level weebs and used");
		weaponLore.add(this.color + " 10000 grit waterstone to");
		weaponLore.add(this.color + "sharpen this sword.");
		weaponLore.add(this.color + "It is now sharper than diamond.");
		weaponMeta.setLore(weaponLore);
		
		weaponMeta.setDisplayName(this.color + "Sharpened Iron Sword");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.IRON_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Iron Helmet");
		this.helmet.setItemMeta(helmetMeta);
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.THORNS, 2, true);
		chestMeta.setDisplayName(this.color + "Reinforced Diamond Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.IRON_LEGGINGS, 1);
		ItemMeta legMeta = this.chestplate.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		legMeta.setDisplayName(this.color + "Reinforced Iron Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.IRON_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Iron Boots");
		
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.15);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 2.0, 0.0, 0.0);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
