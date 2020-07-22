package com.joojet.plugins.mobs.util;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;

public class ConvertColors 
{
	/** Converts a chat color enum to its equivalent color enum */
	public static Color convertChatColorToColor (ChatColor color)
	{
		switch (color)
		{
			case AQUA:
				return Color.AQUA;
			case BLACK:
				return Color.BLACK;
			case BLUE:
				return Color.BLUE;
			case DARK_AQUA:
				return Color.fromRGB(0, 170, 170);
			case DARK_BLUE:
				return Color.fromBGR(170, 0, 0);
			case DARK_GREEN:
				return Color.fromRGB(0, 170, 0);
			case DARK_RED:
				return Color.fromRGB(170, 0, 0);
			case DARK_PURPLE:
				return Color.fromRGB(170, 0, 170);
			case GOLD:
				return Color.fromRGB(255, 170, 0);
			case GRAY:
				return Color.fromRGB(170, 170, 170);
			case DARK_GRAY:
				return Color.fromRGB(85, 85, 85);
			case GREEN:
				return Color.GREEN;
			case LIGHT_PURPLE:
				return Color.fromRGB(255, 85, 255);
			case YELLOW:
				return Color.YELLOW;
			case WHITE:
				return Color.WHITE;
			default:
				return Color.BLACK;
		}
	}
	
	/** Converts a chat color to its equivlant DyeColor enum */
	public static DyeColor convertDyeColorToColor (ChatColor color)
	{
		switch (color)
		{
			case AQUA:
				return DyeColor.LIGHT_BLUE;
			case BLACK:
				return DyeColor.BLACK;
			case BLUE:
				return DyeColor.BLUE;
			case DARK_AQUA:
				return DyeColor.CYAN;
			case DARK_BLUE:
				return DyeColor.BLUE;
			case DARK_GREEN:
				return DyeColor.GREEN;
			case DARK_RED:
				return DyeColor.RED;
			case DARK_PURPLE:
				return DyeColor.PURPLE;
			case GOLD:
				return DyeColor.ORANGE;
			case GRAY:
				return DyeColor.LIGHT_GRAY;
			case DARK_GRAY:
				return DyeColor.GRAY;
			case GREEN:
				return DyeColor.LIME;
			case LIGHT_PURPLE:
				return DyeColor.MAGENTA;
			case YELLOW:
				return DyeColor.YELLOW;
			case WHITE:
				return DyeColor.WHITE;
			default:
				return DyeColor.PINK;
		}
	}
}
