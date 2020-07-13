package com.joojet.plugins.mobs.equipment.head;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class SkullKidHelmet extends Equipment
{
	public SkullKidHelmet (ChatColor color)
	{
		super (PlayerHead.MAJORAS_MASK, color);
		
		this.addDefenseAttributes(3.0, 4.0, 0.25);
		this.addAttackAttributes(0.0, 0.15);
		this.addHealthAttributes(4.0);
		
		Map <Enchantment, Integer> enchants = new HashMap <Enchantment, Integer> ();
		enchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		enchants.put(Enchantment.PROTECTION_PROJECTILE, 4);
		enchants.put(Enchantment.THORNS, 4);
		enchants.put(Enchantment.BINDING_CURSE, 1);
		this.addUnsafeEnchantments(enchants);
		
		this.setDisplayName("Majora's Mask");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
 	}
}
