package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class EnragedSpider extends MobEquipment
{
	public EnragedSpider ()
	{
		this.name = "Enraged Spider";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 20;
		
		// Custom potion effects
		this.effects.add(CustomPotionEffect.STRENGTH.getPotionEffect());
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		
		// Weapon
		this.weapon = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
		weaponMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		
		AttributeModifier weaponMod = new AttributeModifier ("generic.attack_damage", 7, Operation.ADD_NUMBER);
		weaponMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, weaponMod);
		weaponMeta.setDisplayName(this.color + "Fire Venom Fang");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.THORNS, 4, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.setDisplayName(this.color + "Reinforced Keratin Chestplate");
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate);
	}
}
