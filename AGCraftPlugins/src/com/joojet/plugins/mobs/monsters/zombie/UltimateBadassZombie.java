package com.joojet.plugins.mobs.monsters.zombie;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
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
		this.name = "Shadow Clone Jootsu";
		this.color = ChatColor.GOLD;
		
		// Custom potion effects
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.STRENGTH.getPotionEffect());
		
		// Weapon
		this.weapon = new ItemStack(Material.NETHERITE_AXE, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		// Offhand (loot)
		this.offhand = new ItemStack (Material.ANCIENT_DEBRIS, 2);
		
		// Weapon modifier
		AttributeModifier weaponMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_damage", 12.0, Operation.ADD_NUMBER, EquipmentSlot.HAND);
		weaponMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, weaponMod);
		
		// Weapon name and lore
		weaponMeta.setDisplayName(this.color + "A Spiritual Travesty");
		ArrayList <String> weaponLore = new ArrayList <String> ();
		weaponLore.add(this.color + "100% Authentic Japanese Grade Nippon Steel");
		weaponLore.add(this.color + "folded over 9000 times.");
		weaponMeta.setLore(weaponLore);
		
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.NETHERITE_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Netherite Helmet");
		this.helmet.setItemMeta(helmetMeta);
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.addEnchant(Enchantment.THORNS, 3, true);
		chestMeta.setDisplayName(this.color + "Reinforced Netherite Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.NETHERITE_LEGGINGS, 1);
		ItemMeta legMeta = this.chestplate.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		legMeta.setDisplayName(this.color + "Reinforced Netherite Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.NETHERITE_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bootMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Netherite Boots");
		
		// One speedy boi
		AttributeModifier bootsMod = new AttributeModifier (UUID.randomUUID(), "generic.movement_speed", 0.15, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET);
		bootMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, bootsMod);
	
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
