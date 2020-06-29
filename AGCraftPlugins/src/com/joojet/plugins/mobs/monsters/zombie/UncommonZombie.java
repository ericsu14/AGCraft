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

import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UncommonZombie extends MobEquipment 
{
	public UncommonZombie ()
	{
		this.name = "Uncommon Zombie";
		this.color = ChatColor.GREEN;
		
		// Weapon
		this.weapon = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		AttributeModifier weaponMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_damage", 8.0, Operation.ADD_NUMBER, EquipmentSlot.HAND);
		weaponMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, weaponMod);
		weaponMeta.setDisplayName(this.color + "Sharpened Iron Sword");
		
		ArrayList <String> weaponLore = new ArrayList <String> ();
		weaponLore.add(this.color + "These zombies were formally");
		weaponLore.add(this.color + "avengers level weebs and used");
		weaponLore.add(this.color + " 7000 grit waterstone to");
		weaponLore.add(this.color + "sharpen this sword.");
		weaponLore.add(this.color + "It is now as sharp as diamond.");
		weaponMeta.setLore(weaponLore);
		
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Helmet
		this.helmet = new ItemStack (Material.CHAINMAIL_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
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
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		legMeta.setDisplayName(this.color + "Reinforced Chainmail Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.CHAINMAIL_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Chainmail Boots");
		
		// One speedy boi
		AttributeModifier bootsMod = new AttributeModifier (UUID.randomUUID(), "generic.movement_speed", 0.10, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET);
		bootMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, bootsMod);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
