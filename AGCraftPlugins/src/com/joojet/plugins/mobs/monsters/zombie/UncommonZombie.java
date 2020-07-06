package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UncommonZombie extends MobEquipment 
{
	public UncommonZombie ()
	{
		this.name = "Bulky Zombie";
		this.color = ChatColor.GREEN;
		String genericLore = "Reinforced with steel to have better resistance towards high damaging attacks.";
		
		// Weapon
		this.weapon = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		weaponMeta.setDisplayName(this.color + "Enhanced Stone Sword");
		
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.CHAINMAIL_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Chainmail Helmet");
		this.addLoreToItemMeta(helmetMeta, genericLore);
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 2.0, 0.5, 0.0);
		this.helmet.setItemMeta(helmetMeta);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		chestMeta.setDisplayName(this.color + "Reinforced Chainmail Chestplate");
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 5.0, 1.0, 0.0);
		this.addLoreToItemMeta(chestMeta, genericLore);
		this.chestplate.setItemMeta(chestMeta);
		
		// Leggings
		this.leggings = new ItemStack (Material.CHAINMAIL_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		legMeta.setDisplayName(this.color + "Reinforced Chainmail Leggings");
		this.addDefenseAttributes(legMeta, EquipmentSlot.LEGS, 4.0, 0.5, 0.0);
		this.addLoreToItemMeta(legMeta, genericLore);
		this.leggings.setItemMeta(legMeta);
		
		// Boots
		this.boots = new ItemStack (Material.CHAINMAIL_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 2, true);
		bootMeta.setDisplayName(this.color + "Lightweight Chainmail Boots");
		this.addLoreToItemMeta(bootMeta, "Lightweight chains allows for improved mobility.");
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.10);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 1.0, 0.0, 0.0);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
