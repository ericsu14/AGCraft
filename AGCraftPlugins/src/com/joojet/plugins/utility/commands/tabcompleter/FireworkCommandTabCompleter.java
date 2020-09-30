package com.joojet.plugins.utility.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.utility.commands.FireworksCommand;

public class FireworkCommandTabCompleter extends AGTabCompleter
{
	public FireworkCommandTabCompleter ()
	{
		super (CommandType.FIREWORKS);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) 
	{
		int n = args.length - 1;
		String input = args[n];
		
		List <String> result = new ArrayList <String> (); 
		switch (n)
		{
			//radius
			case 0:
				result = this.filterArrayByInput(
						this.generateBetweenRange(1, FireworksCommand.fireworkSpreadLimit).toArray(), 
						input);
				break;
			// power
			case 1:
				result = this.filterArrayByInput(
						this.generateBetweenRange(1, FireworksCommand.fireworkPowerLimit).toArray(), 
						input);
				break;
			// ammo count
			case 2:
				result = this.filterArrayByInput(
						this.generateBetweenRange(FireworksCommand.minFireworkCount, FireworksCommand.fireworkLimit).toArray(), 
						input);
				break;
			// Firework music type
			case 3:
				result = this.filterArrayByInput(MusicListener.getFireworkMusicTypes(), input);
				break;
			default:
				break;
		}
		return result;
	}
	
	/** Returns an array of integers between a certain range
	 * 	@param min - Min value
	 *  @param max - Max value */
	protected List <String> generateBetweenRange (int min, int max)
	{
		List <String> result = new ArrayList <String> ();
		for (int i = min; i <= max; ++i)
		{
			result.add(i + "");
		}
		return result;
	}
}
