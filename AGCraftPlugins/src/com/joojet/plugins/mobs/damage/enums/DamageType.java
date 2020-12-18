package com.joojet.plugins.mobs.damage.enums;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.util.StringUtil;

public enum DamageType 
{
	NORMAL ("", ChatColor.WHITE, ChatColor.WHITE),
	ALLIED ("✪", ChatColor.AQUA, ChatColor.WHITE),
	POISON ("☠", ChatColor.GREEN, ChatColor.GOLD),
	MAGIC ("✴", ChatColor.LIGHT_PURPLE, ChatColor.GOLD),
	HEALING ("♥", ChatColor.LIGHT_PURPLE, ChatColor.GOLD),
	CRITICAL ("✧", ChatColor.GOLD, ChatColor.WHITE),
	FIRE ("🔥", ChatColor.RED, ChatColor.GOLD),
	DROWNING ("°ₒ৹", ChatColor.BLUE, ChatColor.RED),
	WITHER ("☠", ChatColor.DARK_GRAY, ChatColor.DARK_RED),
	EXPLOSION ("✸", ChatColor.YELLOW, ChatColor.GOLD),
	PROJECTILE ("◎", ChatColor.RED, ChatColor.GOLD),
	PROJECTILE_CRITICAL ("◎", ChatColor.LIGHT_PURPLE, ChatColor.GOLD),
	PLAYER ("", ChatColor.RED, ChatColor.RED),
	FALL_DAMAGE ("↯", ChatColor.RED, ChatColor.WHITE),
	THORNS ("🛡", ChatColor.LIGHT_PURPLE, ChatColor.WHITE),
	CONTACT ("✴", ChatColor.GREEN, ChatColor.WHITE),
	LIGHTNING ("ϟ", ChatColor.GOLD, ChatColor.WHITE),
	SUICIDE ("☠", ChatColor.RED, ChatColor.DARK_RED),
	HUNGER ("☹ ", ChatColor.YELLOW, ChatColor.RED),
	TRIDENT ("⋔", ChatColor.AQUA, ChatColor.GOLD);
	
	/** Color applied on the symbol of the damage type */
	private ChatColor symbolColor;
	/** Symbol appended in the beginning of the damage display */
	private String symbol;
	/** Color applied to the damage number */
	private ChatColor damageColor;
	
	private DamageType (String symbol, ChatColor symbolColor, ChatColor damageColor)
	{
		this.symbol = symbol;
		this.symbolColor = symbolColor;
		this.damageColor = damageColor;
	}
	
	@Override
	public String toString ()
	{
		return this.symbolColor + this.symbol;
	}
	
	public String getReversedSymbols ()
	{
		return this.symbolColor + StringUtil.reverseString(this.symbol);
	}
	
	public ChatColor getDamageColor ()
	{
		return this.damageColor;
	}
	
	public boolean hasSymbol ()
	{
		return !this.symbol.isEmpty();
	}
}
