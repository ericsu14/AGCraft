package com.joojet.plugins.mobs.damage.enums;

import org.bukkit.ChatColor;

public enum DamageType 
{
	NORMAL ("", ChatColor.RESET),
	ALLIED ("✪", ChatColor.AQUA),
	POISON ("☠", ChatColor.GREEN),
	MAGIC ("✴", ChatColor.LIGHT_PURPLE),
	HEALING ("❤", ChatColor.LIGHT_PURPLE),
	CRITICAL ("✰", ChatColor.GOLD),
	FIRE ("❂", ChatColor.RED),
	DROWNING ("◎", ChatColor.BLUE),
	WITHER ("☠", ChatColor.GRAY);
	
	/** Color applied on the symbol of the damage type */
	private ChatColor color;
	/** Symbol appended in the beginning of the damage display */
	private String symbol;
	
	private DamageType (String symbol, ChatColor color)
	{
		this.symbol = symbol;
		this.color = color;
	}
	
	@Override
	public String toString ()
	{
		return this.color + this.symbol;
	}
}
