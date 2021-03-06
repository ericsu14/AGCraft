package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class BruinBanditHead extends Equipment 
{
	public BruinBanditHead ()
	{
		super (EquipmentType.BRUIN_BANDIT_HEAD, PlayerHead.UCLA_BANDIT, ChatColor.AQUA);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName(UCLAFaction.generateUCLADisplayName("Bandit"));
		this.addLoreToItemMeta("At least he is practicing social distancing...");
		this.addDefenseAttributes(2.0, 2.0, 0.15);
		this.addAttackAttributes(1.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 1);
	}
}
