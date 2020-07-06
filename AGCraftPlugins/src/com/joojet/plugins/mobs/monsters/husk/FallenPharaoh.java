package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class FallenPharaoh extends MobEquipment
{
	public FallenPharaoh ()
	{
		this.name = "Fallen Pharaoh";
		this.color = ChatColor.LIGHT_PURPLE;
		this.wordsPerLine = 3;
		
		this.helmet = new ItemStack (Material.PLAYER_HEAD, 1);
		ItemMeta helmetMeta = this.createHeadData(this.helmet, PlayerHead.PHARAOH);
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 2.0, 1.0, 0.05);
		helmetMeta.setDisplayName(this.color + "Head of the Fallen Pharaoh");
		this.addLoreToItemMeta(helmetMeta, "Once a ruler, fallen from grace.");
		this.helmet.setItemMeta(helmetMeta);
		
		this.chestplate = new ItemStack (Material.GOLDEN_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		chestMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		chestMeta.setDisplayName(this.color + "Royal Gold Chestplate");
		this.addLoreToItemMeta(chestMeta, "Passed down by generations.");
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 6.0, 2.0, 0.10);
		this.chestplate.setItemMeta(chestMeta);
		
		this.leggings = new ItemStack (Material.GOLDEN_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_FIRE, 3, true);
		legMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		legMeta.setDisplayName(this.color + "Royal Gold Leggings");
		this.addLoreToItemMeta(legMeta, "Passed down by generations.");
		this.addDefenseAttributes(legMeta, EquipmentSlot.LEGS, 5.0, 2.0, 0.05);
		this.leggings.setItemMeta(legMeta);
		
		this.boots = new ItemStack (Material.GOLDEN_BOOTS, 1);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		bootMeta.setDisplayName(this.color + "Royal Gold Boots");
		this.addLoreToItemMeta(bootMeta, "This isn't actually made from 24k gold which offers vastly improved mobility.");
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 2.0, 1.0, 0.05);
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.20);
		this.boots.setItemMeta(bootMeta);
		
		this.weapon = new ItemStack (Material.GOLDEN_HOE, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		this.addAttackAttributes(weaponMeta, EquipmentSlot.HAND, 6.0, 1.4);
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		weaponMeta.setDisplayName(this.color + "Pharaoh's Royal Staff");
		this.addLoreToItemMeta(weaponMeta, "They say you don't waste gold on a hoe.");
		this.weapon.setItemMeta(weaponMeta);
	}
}
