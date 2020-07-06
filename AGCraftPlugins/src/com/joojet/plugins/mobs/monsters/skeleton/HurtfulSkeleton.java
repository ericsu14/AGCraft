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

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class HurtfulSkeleton extends MobEquipment  
{
	public HurtfulSkeleton ()
	{
		this.name = "Hurtful Skeleton";
		this.color = ChatColor.BLUE;
		String genericLore = "Reinforced with titanium to have better resistance towards high damaging attacks.";
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new ItemStack(Material.BOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
		weaponMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		weaponMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
		
		weaponMeta.setDisplayName(this.color + "Hurtful Bow");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.MAROON);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 0), true);
		tippedArrow.setDisplayName(this.color + "Hurtful Arrow");
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new ItemStack (Material.IRON_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Iron Helmet");
		this.addLoreToItemMeta(helmetMeta, genericLore);
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 0.0, 0.5, 0.0);
		this.helmet.setItemMeta(helmetMeta);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.IRON_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, true);
		chestMeta.setDisplayName(this.color + "Bulletproof Iron Chestplate");
		this.addLoreToItemMeta(chestMeta, genericLore);
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 0.0, 1.0, 0.0);
		this.chestplate.setItemMeta(chestMeta);

		// Leggings
		this.leggings = new ItemStack (Material.IRON_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		legMeta.setDisplayName(this.color + "Reinforced Iron Leggings");
		this.addLoreToItemMeta(legMeta, genericLore);
		this.addDefenseAttributes(legMeta, EquipmentSlot.LEGS, 0.0, 0.5, 0.0);
		this.leggings.setItemMeta(legMeta);
		
		// Boots
		this.boots = new ItemStack (Material.IRON_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		this.addLoreToItemMeta(bootMeta, "Lightweight iron allows for improved mobility.");
		bootMeta.setDisplayName(this.color + "Lightweight Chainmail Boots");
		
		// One speedy boi
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.15);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 2.0, 0.0, 0.0);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
