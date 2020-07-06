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
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class UltimateBadassSkeleton extends MobEquipment
{
	public UltimateBadassSkeleton ()
	{
		this.name = "#agspotted";
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new ItemStack(Material.BOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
		weaponMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		weaponMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
		
		weaponMeta.setDisplayName(this.color + "A Spiritual Fantasy");
		this.addLoreToItemMeta(weaponMeta, "Don't even think about it.");
		
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setColor(Color.fromRGB(75, 0, 130));
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.WITHER, 70, 2), true);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 1), true);
		tippedArrow.setDisplayName(this.color + "Thanos Arrow");
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new ItemStack (Material.PLAYER_HEAD, 1);
		ItemMeta helmetMeta = this.createHeadData(this.helmet, PlayerHead.CAMERA_HEAD);
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		this.addLoreToItemMeta(helmetMeta, "Used for stalker pics and spottings.");
		helmetMeta.setDisplayName(this.color + "#agspotted Camera");
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 3.0, 3.0, 0.15);
		this.helmet.setItemMeta(helmetMeta);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		chestMeta.setDisplayName(this.color + "Dark Netherite Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.NETHERITE_LEGGINGS, 1);
		ItemMeta legMeta = this.leggings.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		legMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		legMeta.setDisplayName(this.color + "Dark Netherite Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.NETHERITE_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.setDisplayName(this.color + "Lightweight Netherite Boots");
		
		this.addSpeedAttribute(bootMeta, EquipmentSlot.FEET, 0.20);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 3.0, 3.0, 0.1);
		
		// Add back defences for boots
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
