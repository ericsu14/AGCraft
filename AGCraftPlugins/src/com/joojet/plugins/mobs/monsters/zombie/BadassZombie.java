package com.joojet.plugins.mobs.monsters.zombie;

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
		String genericLore = "Reinforced with titanium to have better resistance towards high damaging attacks.";
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		
		// Weapon
		this.weapon = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		
		this.addAttackAttributes(weaponMeta, EquipmentSlot.HAND, 7.0, 2.0);
		
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		this.addLoreToItemMeta(weaponMeta, "Sharpened with a 10000 grit waterstone, these swords deal just as much damage as a Diamond Sword.");
		weaponMeta.setDisplayName(this.color + "Sharpened Iron Sword");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.IRON_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		helmetMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		helmetMeta.setDisplayName(this.color + "Enhanced Iron Helmet");
		this.addLoreToItemMeta(helmetMeta, genericLore);
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 2.0, 1.0, 0.0);
		this.helmet.setItemMeta(helmetMeta);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		chestMeta.addEnchant(Enchantment.THORNS, 2, true);
		chestMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		chestMeta.setDisplayName(this.color + "Enhanced Diamond Chestplate");
		this.addLoreToItemMeta(chestMeta, "Forged from a higher-grade cut of Diamond, this chestplate offers improved resistance towards high damaging attacks.");
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 8.0, 4.0, 0.0);
		this.chestplate.setItemMeta(chestMeta);
		
		// Leggings
		this.leggings = new ItemStack (Material.IRON_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		legMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		legMeta.setDisplayName(this.color + "Enhanced Iron Leggings");
		this.addLoreToItemMeta(legMeta, genericLore);
		this.addDefenseAttributes(legMeta, EquipmentSlot.LEGS, 5.0, 1.0, 0.0);
		this.leggings.setItemMeta(legMeta);
		
		// Boots
		this.boots = new ItemStack (Material.IRON_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Iron Boots");
		this.addLoreToItemMeta(bootMeta, "Lightweight iron allows for improved mobility.");
		
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.15);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 2.0, 0.5, 0.0);
		
		this.boots.setItemMeta(bootMeta);;
	}
}
