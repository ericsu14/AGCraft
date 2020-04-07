package com.joojet.plugins.warp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.World.Environment;
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
			insert (p.getUniqueId().toString(), locationName, loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getEnvironment(), level);
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
	public static void insert (String uuid, String locationName, double x, double y, double z, Environment env, WarpAccessLevel level) throws SQLException
	{
		locationName = locationName.toLowerCase();
		
		// TODO: Check if the locationName already exists in the database. 
		if (!checkIfLocationExists (uuid, locationName, env))
		{
			throw new RuntimeException (locationName + " is already a registered location.");
		}
		
		Connection c = null;
		
		c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		c.setAutoCommit(false);
			
		StringBuilder query = new StringBuilder ("INSERT INTO LOCATIONS (UUID, NAME, X, Y, Z, WORLD, ACCESS) VALUES (?,?,?,?,?,?,?)");
			
		PreparedStatement pstmt = c.prepareStatement(query.toString());
			
		pstmt.setString(1, uuid);
		pstmt.setString(2, locationName);
		pstmt.setDouble(3, x);
		pstmt.setDouble(4, y);
		pstmt.setDouble(5, z);
		pstmt.setString(6, env.name());
		pstmt.setString(7, level.toString());
		pstmt.executeUpdate();
			
		pstmt.close();
		c.commit();
		c.close();
	}
	
	/** Searches the database to see if:
	 * 		- Either the private locationName registered under the user exists
	 * 		- or a public locationName registered under any player exists.
	 * This will return the resultSet formed from this query 
	 * @throws SQLException */
	public static ResultSet fetchLocation (String uuid, String locationName, Environment env) throws SQLException
	{
		locationName = locationName.toLowerCase();
		
		Connection c = null;
		ResultSet result = null;
		
		c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ("SELECT 1 FROM LOCATIONS WHERE ");
		query.append("(UUID = ? AND NAME = ? AND WORLD = ? AND ACCESS = ?)");
		query.append("OR");
		query.append("(NAME = ? AND WORLD = ? AND ACCESS = ?)");
		
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		pstmt.setString(2, locationName);
		pstmt.setString(3, env.name());
		pstmt.setString(4, WarpAccessLevel.PRIVATE.name());
		pstmt.setString(5, locationName);
		pstmt.setString(6, env.name());
		pstmt.setString(7, WarpAccessLevel.PUBLIC.name());
		pstmt.execute();
		result = pstmt.executeQuery();
		
		pstmt.close();
		c.close();
		
		return result;
	}
	
	/** Checks if a player specified location under a name already exists in the database */
	public static boolean checkIfLocationExists (String uuid, String locationName, Environment env)
	{
		try 
		{
			return fetchLocation(uuid, locationName, env).next();
		} 
		catch (SQLException e) 
		{
			System.out.println (e.getMessage());
		}
		return false;
	}
	
	/** Attempts to fetch and return the location data that is tied under locationName from the database. */
	public static Location getlocation (Player p, String locationName) throws SQLException, RuntimeException
	{
		ResultSet data = fetchLocation (p.getUniqueId().toString(), locationName, p.getWorld().getEnvironment());
		if (!data.next())
		{
			throw new RuntimeException (locationName + " is not a registered location.");
		}
		
		double x = data.getDouble("X");
		double y = data.getDouble("Y");
		double z = data.getDouble("Z");
		
		Location loc = new Location (p.getWorld(), x, y, z);
		
		return loc;
	}
}
