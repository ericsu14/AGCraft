package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class HurtfulSkeleton extends MobEquipment  
{
	public HurtfulSkeleton ()
	{
		this.name = "Hurtful Skeleton";
		this.color = ChatColor.LIGHT_PURPLE;
		
		// Weapon
		this.weapon = new ItemStack(Material.BOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
		weaponMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		weaponMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
		weaponMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		
		weaponMeta.setDisplayName(this.color + "Chaos Bow");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setBasePotionData(new PotionData (PotionType.INSTANT_DAMAGE));
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 1), true);
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new ItemStack (Material.IRON_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Reinforced Iron Helmet");
		this.helmet.setItemMeta(helmetMeta);
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.IRON_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.setDisplayName(this.color + "Bulletproof Iron Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
		// Leggings
		this.leggings = new ItemStack (Material.IRON_LEGGINGS, 1);
		ItemMeta legMeta = this.chestplate.getItemMeta();
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		legMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		legMeta.setDisplayName(this.color + "Reinforced Iron Leggings");
		this.leggings.setItemMeta(legMeta);
		this.addRandomDamage(this.leggings);
		
		// Boots
		this.boots = new ItemStack (Material.IRON_BOOTS);
		ItemMeta bootMeta = this.boots.getItemMeta();
		bootMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		bootMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		bootMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		bootMeta.setDisplayName(this.color + "Lightweight Chainmail Boots");
		
		// One speedy boi
		AttributeModifier bootsMod = new AttributeModifier ("generic.movement_speed", 0.10, Operation.ADD_NUMBER);
		bootMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, bootsMod);
		
		this.boots.setItemMeta(bootMeta);
		this.addRandomDamage(this.boots);
	}
}
