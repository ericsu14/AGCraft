package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UltimateBadassZombie extends MobEquipment
{
	public UltimateBadassZombie ()
	{
		this.name = "Shadow Clone joojetsu";
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new ItemStack(Material.NETHERITE_AXE, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		// Weapon modifier
		this.addAttackAttributes(weaponMeta, EquipmentSlot.HAND, 11.0, 3.0);
		
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		// Weapon name and lore
		weaponMeta.setDisplayName(this.color + "A Spiritual Travesty");
		this.addLoreToItemMeta(weaponMeta, "Forged from Glorious Nippon Steel, folded over 9000 times");
		
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.NETHERITE_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Dark Netherite Helmet");
		this.helmet.setItemMeta(helmetMeta);
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.addEnchant(Enchantment.THORNS, 2, true);
		chestMeta.setDisplayName(this.color + "Dark Netherite Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.NETHERITE_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true);
		legMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		legMeta.setDisplayName(this.color + "Dark Netherite Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.NETHERITE_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Netherite Boots");
		this.addLoreToItemMeta(bootMeta, "Forged from a mixture of carbon-fiber and netherite, these boots offers vastly improved mobility.");
		
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.20);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 3.0, 3.0, 0.1);
	
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
