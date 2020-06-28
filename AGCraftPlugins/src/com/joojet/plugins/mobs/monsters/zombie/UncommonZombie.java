package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
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
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		weaponMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		
		AttributeModifier weaponMod = new AttributeModifier ("generic.attack_damage", 7.0, Operation.ADD_NUMBER);
		weaponMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, weaponMod);
		weaponMeta.setDisplayName(this.color + "Reinforced Iron Sword");
		this.addRandomDamage(this.offhand);
		
		AttributeModifier toughness = new AttributeModifier ("generic.armorToughness", 2.0, Operation.ADD_NUMBER);
		// Helmet
		this.helmet = new ItemStack (Material.CHAINMAIL_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughness);
		helmetMeta.setDisplayName(this.color + "Reinforced Chainmail Helmet");
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.CHAINMAIL_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughness);
		chestMeta.setDisplayName(this.color + "Reinforced Chainmail Chestplate");
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.CHAINMAIL_LEGGINGS, 1);
		ItemMeta legMeta = this.chestplate.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		legMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		legMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughness);
		legMeta.setDisplayName(this.color + "Reinforced Chainmail Leggings");
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.CHAINMAIL_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		bootMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughness);
		bootMeta.setDisplayName(this.color + "Reinforced Chainmail Boots");
		this.addRandomDamage(this.boots);
	}
}
