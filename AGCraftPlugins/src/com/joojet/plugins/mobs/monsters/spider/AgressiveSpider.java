package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class AgressiveSpider extends MobEquipment
{
	public AgressiveSpider ()
	{
		this.health = 20;
		this.name = "Agressive Spider";
		this.color = ChatColor.GREEN;
		
		this.effects.add(CustomPotionEffect.RESISTANCE.getPotionEffect());
		
		// Weapon
		this.weapon = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		
		this.addAttackAttributes(weaponMeta, EquipmentSlot.HAND, 8.0, 1.6);
		
		weaponMeta.setDisplayName(this.color + "Hardened Spider Fang");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
	}
}
