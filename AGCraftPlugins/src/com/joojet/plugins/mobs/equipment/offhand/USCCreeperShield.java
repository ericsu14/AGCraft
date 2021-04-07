package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.ShieldEquipment;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class USCCreeperShield extends ShieldEquipment 
{
	public USCCreeperShield ()
	{
		super (EquipmentType.USC_CREEPER_SHIELD, DyeColor.RED, EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		this.setDisplayName(USCFaction.generateUSCDisplayName("Creeper Shield"));
		this.addLoreToItemMeta("Even the USC Trojans are huge fans of Minecraft! #fighton");
		this.addPatterns(new Pattern (DyeColor.YELLOW, PatternType.CREEPER),
				new Pattern (DyeColor.YELLOW, PatternType.CREEPER));
		
		this.addItemFlagstoMeta(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
		this.makeUnbreakable();
		this.addAttackAttributes(2.0, 0.0);
		this.addDefenseAttributes(0.0, 3.0, 0.10);
		this.addHealthAttributes(10.0);
		this.makeSoulbound();
	}
}
