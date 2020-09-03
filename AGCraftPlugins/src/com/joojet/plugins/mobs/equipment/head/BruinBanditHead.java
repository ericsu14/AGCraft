package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BruinBanditHead extends Equipment 
{
	public BruinBanditHead ()
	{
		super (EquipmentTypes.BRUIN_BANDIT_HEAD, PlayerHead.UCLA_BANDIT, ChatColor.AQUA);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName("Head of the UCLA Jock");
		this.addLoreToItemMeta("At least he is practicing social distancing...");
		this.addDefenseAttributes(2.0, 2.0, 0.05);
		this.addAttackAttributes(1.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 1);
	}
}
