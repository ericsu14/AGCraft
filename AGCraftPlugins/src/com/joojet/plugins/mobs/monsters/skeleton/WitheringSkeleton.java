package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WitheringSkeleton extends MobEquipment 
{
	public WitheringSkeleton ()
	{
		this.name = "Rare Withering Skeleton";
		this.color = ChatColor.BLUE;
		this.health = 30;
		
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		
		// Weapon
		this.weapon = new ItemStack(Material.BOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
		weaponMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		
		weaponMeta.setDisplayName(this.color + "Chaos Bow");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Offhand
		this.offhand = new ItemStack (Material.TIPPED_ARROW, 1);
		PotionMeta tippedArrow = (PotionMeta) this.offhand.getItemMeta();
		tippedArrow.setBasePotionData(new PotionData (PotionType.INSTANT_DAMAGE));
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.WITHER, 140, 1), true);
		tippedArrow.addCustomEffect(new PotionEffect (PotionEffectType.HARM, 1, 0), true);
		this.offhand.setItemMeta(tippedArrow);
		
		// Helmet
		this.helmet = new ItemStack (Material.NETHERITE_HELMET, 1);
		ItemMeta helmetMeta = this.helmet.getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		helmetMeta.setDisplayName(this.color + "Dark Netherite Helmet");
		this.helmet.setItemMeta(helmetMeta);
		this.addRandomDamage(this.helmet);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.setDisplayName(this.color + "Dark Netherite Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
		
	}
}
