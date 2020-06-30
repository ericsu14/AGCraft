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
		
		// Weapon
		this.weapon = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		
		this.addAttackAttributes(weaponMeta, EquipmentSlot.HAND, 7.0, 2.2);
		
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		weaponMeta.setDisplayName(this.color + "Enhanced Iron Sword");
		
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.CHAINMAIL_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Chainmail Helmet");
		this.helmet.setItemMeta(helmetMeta);
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.setDisplayName(this.color + "Reinforced Chainmail Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.CHAINMAIL_LEGGINGS, 1);
		ItemMeta legMeta = this.chestplate.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		legMeta.setDisplayName(this.color + "Reinforced Chainmail Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.CHAINMAIL_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Chainmail Boots");
		
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.10);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 1.0, 0.0, 0.0);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
