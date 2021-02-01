package com.joojet.plugins.mobs.enums;

import org.bukkit.ChatColor;

public enum MonsterClassifier 
{
	COMMON (0.0, ChatColor.GRAY),
	UNCOMMON (0.35, ChatColor.GREEN),
	RARE (0.45, ChatColor.BLUE),
	EPIC (0.60, ChatColor.LIGHT_PURPLE),
	LEGENDARY (0.70, ChatColor.GOLD),
	MYTHIC (0.825, ChatColor.DARK_RED);
	
	/** Required threshold needed to be reached in order for the monster under this classifier is able to naturally
	 *  spawn. */
	protected Double threshold;
	/** Nametag color used for this classifier */
	protected ChatColor color;
	
	private MonsterClassifier (Double threshold, ChatColor color)
	{
		this.threshold = threshold;
		this.color = color;
	}
	
	public Double getThreshold ()
	{
		return this.threshold;
	}
	
	public ChatColor getChatColor ()
	{
		return this.color;
	}
}
