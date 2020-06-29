package com.joojet.plugins.mobs.monsters.spider;

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

		AttributeModifier weaponMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_damage", 8, Operation.ADD_NUMBER, EquipmentSlot.HAND);
		weaponMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, weaponMod);
		
		weaponMeta.setDisplayName(this.color + "Hardened Spider Fang");
		this.weapon.setItemMeta(weaponMeta);
		this.addRandomDamage(this.weapon);
		
	}
}
