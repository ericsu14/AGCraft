package com.joojet.plugins.mobs.damage.enums;

import org.bukkit.ChatColor;

public enum DamageType 
{
	NORMAL ("", ChatColor.WHITE, ChatColor.WHITE),
	ALLIED ("✪", ChatColor.AQUA, ChatColor.WHITE),
	POISON ("☠", ChatColor.GREEN, ChatColor.WHITE),
	MAGIC ("✴", ChatColor.LIGHT_PURPLE, ChatColor.GOLD),
	HEALING ("❤", ChatColor.LIGHT_PURPLE, ChatColor.GREEN),
	CRITICAL ("✧", ChatColor.GOLD, ChatColor.RED),
	FIRE ("🔥", ChatColor.RED, ChatColor.GOLD),
	DROWNING ("°ₒ৹", ChatColor.BLUE, ChatColor.RED),
	WITHER ("☠", ChatColor.GRAY, ChatColor.DARK_RED),
	EXPLOSION ("✸", ChatColor.YELLOW, ChatColor.RED),
	PROJECTILE ("➳", ChatColor.GOLD, ChatColor.RED),
	PLAYER ("⚔️", ChatColor.RED, ChatColor.GOLD),
	FALL_DAMAGE ("✸👞", ChatColor.RED, ChatColor.WHITE);
	
	/** Color applied on the symbol of the damage type */
	private ChatColor color;
	/** Symbol appended in the beginning of the damage display */
	private String symbol;
	/** Color applied to the damage number */
	private ChatColor damageColor;
	
	private DamageType (String symbol, ChatColor color, ChatColor damageColor)
	{
		this.symbol = symbol;
		this.color = color;
		this.damageColor = damageColor;
	}
	
	@Override
	public String toString ()
	{
		return this.color + this.symbol;
	}
	
	public ChatColor getDamageColor ()
	{
		return this.damageColor;
	}
}
