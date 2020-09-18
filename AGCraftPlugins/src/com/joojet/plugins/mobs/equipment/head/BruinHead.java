package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class BruinHead extends Equipment
{
	public BruinHead ()
	{
		super (EquipmentTypes.BRUIN_HEAD, PlayerHead.UCLA_BRUIN, ChatColor.AQUA);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName(UCLAFaction.generateUCLADisplayName("Football Helmet"));
		this.addLoreToItemMeta("This is one school you definitely don't want to see winnin'");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addDefenseAttributes(3.0, 2.0, 0.15);
		this.addAttackAttributes(2.0, 0.0);
	}
}
