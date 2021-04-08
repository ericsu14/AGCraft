package com.joojet.plugins.agcraft.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.command.CommandSender;

public interface AsyncTabCompleteOperation
{
	/** Asynchronously retrieves data from a database operation */
	public List<String> getDataFromDatabase (CommandSender sender, String alias, List <String> parameters) throws SQLException;
}
