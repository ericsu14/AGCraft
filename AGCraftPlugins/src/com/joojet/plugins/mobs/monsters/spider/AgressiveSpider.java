package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		weaponMeta.setDisplayName(this.color + "Spider Tooth");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
	}
}
