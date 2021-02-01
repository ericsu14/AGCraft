package com.joojet.plugins.utility.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.spawning.FairSpawnController;

/** A player command that tells the player his/her current threat score,
 *  along with the classifications of custom monsters that can spawn around
 *  the player. */
public class WhoCanSpawn extends AGCommandExecutor 
{
	/** Used to simulate the player's threat score calculation */
	protected FairSpawnController fairSpawnController;
	
	public WhoCanSpawn() 
	{
		super(CommandType.WHO_CAN_SPAWN);
		this.fairSpawnController = new FairSpawnController (128);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			double averageThreatScore = this.fairSpawnController.getAverageThreatScore(player);
			int nearbyPlayers = this.fairSpawnController.getNearbyPlayers(player);
			
			player.sendMessage(ChatColor.AQUA + "Individual threat score: " + ChatColor.GOLD +  this.fairSpawnController.getIndividualThreatScore(player));
			
			if (nearbyPlayers > 0)
			{
				player.sendMessage(ChatColor.AQUA + "Combined threat score: " + ChatColor.GOLD + averageThreatScore);
				player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "(There are " + ChatColor.GOLD + nearbyPlayers + ChatColor.AQUA +" affecting the combined threat score)");
			}
			
			player.sendMessage(ChatColor.AQUA + "Classifications of monsters that can spawn around you: ");
			StringBuilder classifications = new StringBuilder ();
			for (MonsterClassifier classifier : MonsterClassifier.values())
			{
				if (averageThreatScore <= classifier.getThreshold())
				{
					classifications.append(classifier.getChatColor());
					classifications.append(ChatColor.BOLD);
					classifications.append(classifier.toString().toUpperCase());
					classifications.append("  ");
				}
			}
			player.sendMessage(classifications.toString().trim());
			return true;
		}
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		this.fairSpawnController.setMaxRadius(config.getValueAsInteger(FairSpawnController.CUSTOM_MOB_SCAN_RADIUS));
	}

}
