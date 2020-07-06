package com.joojet.plugins.mobs.monsters.skeleton;


import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UncommonSkeleton extends MobEquipment
{
	public UncommonSkeleton ()
	{
		this.name = "Bulky Skeleton";
		this.color = ChatColor.GREEN;
		this.health = 16;
		String genericLore = "Reinforced with steel to have better resistance towards high damaging attacks.";
		
		// Weapon
		this.weapon = new ItemStack(Material.BOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
		
		weaponMeta.setDisplayName(this.color + "Potent Bow");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.GRAY);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.WEAKNESS, 70, 0), true);
		tippedArrow.setDisplayName(this.color + "Weakening Arrow");
		this.addLoreToItemMeta(tippedArrow, "Weakens the enemy for a short amount of time.");
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new ItemStack (Material.CHAINMAIL_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		helmetMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Chainmail Helmet");
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 0.0, 0.5, 0.0);
		this.addLoreToItemMeta(helmetMeta, genericLore);
		this.helmet.setItemMeta(helmetMeta);
		
		
		// Chestplate
		this.chestplate = new ItemStack (Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
		chestMeta.setDisplayName(this.color + "Bulletproof Chainmail Chestplate");
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 0.0, 1.0, 0.0);
		this.addLoreToItemMeta(chestMeta, genericLore);
		this.chestplate.setItemMeta(chestMeta);
		
		// Leggings
		this.leggings = new ItemStack (Material.CHAINMAIL_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		legMeta.setDisplayName(this.color + "Reinforced Chainmail Leggings");
		this.addDefenseAttributes(legMeta, EquipmentSlot.LEGS, 0.0, 0.5, 0.0);
		this.addLoreToItemMeta(legMeta, genericLore);
		this.leggings.setItemMeta(legMeta);
		
		// Boots
		this.boots = new ItemStack (Material.CHAINMAIL_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 3, true);
		bootMeta.setDisplayName(this.color + "Lightweight Chainmail Boots");
		this.addLoreToItemMeta(bootMeta, "Lightweight chains allows for improved mobility.");
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.10);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 1.0, 0.5, 0.0);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
