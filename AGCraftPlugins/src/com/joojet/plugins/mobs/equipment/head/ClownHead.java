package com.joojet.plugins.mobs.equipment.head;

import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ClownHead extends Equipment 
{
	public ClownHead (ChatColor color, Calendar expirationTimestamp)
	{
		super (PlayerHead.CLOWN, color);
		this.setDisplayName("#AGClown");
		StringBuilder lore = new StringBuilder ();
		lore.append("BAD SPORT! Because of your recent actions in this server,");
		lore.append(" you will be forced to wear this clown head as a consequence for ");
		lore.append("your actions. This head will disappear from your inventory on: ");
		lore.append(ChatColor.AQUA);
		lore.append(expirationTimestamp.get(Calendar.MONTH + 1));
		lore.append("/");
		lore.append(expirationTimestamp.get(Calendar.DAY_OF_MONTH));
		lore.append("/");
		lore.append(expirationTimestamp.get(Calendar.YEAR));
		this.addLoreToItemMeta(lore.toString());
		this.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.setLocalizedName("clown");
	}
}
