package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class EternalJoojHead extends Equipment 
{
	public EternalJoojHead (ChatColor color)
	{
		super (EquipmentType.ETERNAL_JOOJ_HEAD, PlayerHead.ETERNAL_SHADOW_CLONE_JOOJETSU, color);
		this.setDisplayName("Eternal Shadow Clone joojetsu");
		this.addDefenseAttributes(4.0, 7.0, 0.30);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
		this.addUnsafeEnchantment(Enchantment.THORNS, 5);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
		this.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.RED;
		this.addLoreToItemMeta("The one who once wore this helmet chose the path of perpetual torment, seeking vengance against all those who wronged him.");
		this.makeSoulbound();
	}
}
