package com.joojet.plugins.warp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.CreateLocationDatabase;

public class LocationDatabaseManager 
{
	/** Inserts the player's current location into the database */
	public static void insert (Player p, String locationName, WarpAccessLevel level)
	{
		Location loc = p.getLocation();
		try
		{
			insert (p.getUniqueId().toString(), locationName, loc.getX(), loc.getY(), loc.getZ(), level);
		}
		
		catch (SQLException e)
		{
			p.sendMessage ("An internal error occured while attempting to register this new location.");
			System.out.println ("Error: " + e.getMessage());
			
			CreateLocationDatabase.createDataBase();
		}
	}
	
	/** Inserts a new location into the database 
	 * @throws SQLException */
	public static void insert (String uuid, String locationName, double x, double y, double z, WarpAccessLevel level) throws SQLException
	{
		// TODO: Check if the locationName already exists in the database. 
		
		Connection c = null;
		
		c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		c.setAutoCommit(false);
			
		StringBuilder query = new StringBuilder ("INSERT INTO LOCATIONS (UUID, NAME, X, Y, Z, ACCESS) VALUES (?,?,?,?,?,?)");
			
		PreparedStatement pstmt = c.prepareStatement(query.toString());
			
		pstmt.setString(1, uuid);
		pstmt.setString(2, locationName.toLowerCase());
		pstmt.setDouble(3, x);
		pstmt.setDouble(4, y);
		pstmt.setDouble(5, z);
		pstmt.setString(6, level.toString());
		pstmt.executeUpdate();
			
		pstmt.close();
		c.commit();
		c.close();
	}
	
	/** Checks if a player specified location under a name already exists in the database */
	
}
