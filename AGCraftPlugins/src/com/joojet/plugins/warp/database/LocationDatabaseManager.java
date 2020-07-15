package com.joojet.plugins.warp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.warp.constants.WarpAccessLevel;

public class LocationDatabaseManager 
{
	/** Inserts the player's current location into the database
	 * 	@param p - The player object
	 * 	@param locationName - Name of the location the player is setting their location as
	 * 	@param level - The access priv. level the player is setting their location as */
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
			
			CreateLocationDatabase.createDatabase();
		}
	}
	
	/** Inserts a new location into the database
	 * 	@param uuid - Player's unique UUID
	 * 	@param locationName - Name of the location the player is setting their location as
	 * 	@param x - X coord of location
	 * 	@param y - Y coord of location
	 * 	@param z - Z coord of location
	 * 	@param env - The dimension the player is in
	 * 	@param level - The access priv. level the player is setting their location as
	 * @throws SQLException */
	public static void insert (String uuid, String locationName, double x, double y, double z, Environment env, WarpAccessLevel level) throws SQLException, RuntimeException
	{
		locationName = locationName.toLowerCase();
		
		if (checkIfLocationExists (uuid, locationName, env))
		{
			throw new RuntimeException (locationName + " is already a registered location in " + GetCoordinates.getEnvironmentName(env));
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
	
	/** Removes a location entry from the database
	 * 	@param uuid - Player's unique UUID
	 * 	@param locationName - Name of the location the player is setting their location as
	 * 	@param env - The dimension the player is in */
	public static void removeLocation (String uuid, String locationName, Environment env) throws SQLException, RuntimeException
	{
		locationName = locationName.toLowerCase();
		
		if (!checkIfLocationUnderClassifierExists (uuid, locationName, env, WarpAccessLevel.PRIVATE) 
				&& !checkIfLocationUnderClassifierExists (uuid, locationName, env, WarpAccessLevel.PUBLIC))
		{
			throw new RuntimeException ("Either " + locationName + " is a public location not set by you or " +
					 locationName +" is not a registered location in " + GetCoordinates.getEnvironmentName(env));
		}
		
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ("DELETE FROM LOCATIONS WHERE ");
		query.append("UUID = ? AND NAME = ? AND WORLD = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		pstmt.setString(2, locationName);
		pstmt.setString(3, env.name());
		
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	/** Removes all locations under a specific environment (world) from the database
	 *  This is mostly used to remove all old nether locations from the DB safely. */
	public static void removeAllLocationsUnderEnviroment (Environment env) throws SQLException, RuntimeException
	{
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ("DELETE FROM LOCATIONS WHERE ");
		query.append ("WORLD = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, env.name());
		
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	/** Searches the database to see if:
	 * 		- Either the private locationName registered under the user exists
	 * 		- or a public locationName registered under any player exists.
	 * This will return the location data as a LocationEntry. null if not found.
	 * 	@param uuid - UUID of the player
	 * 	@param locationName - Name of the location the player is setting their location as
	 * 	@param env - The dimension the player is in
	 * @throws SQLException */
	public static LocationEntry fetchLocation (String uuid, String locationName, Environment env) throws SQLException
	{
		locationName = locationName.toLowerCase();
		
		Connection c = null;
		ResultSet result = null;
		LocationEntry entry = null;
		
		ArrayList <LocationEntry> entries = new ArrayList <LocationEntry> ();
		
		c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ("SELECT * FROM LOCATIONS WHERE ");
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
		
		if (result.next())
		{
			do
			{
				entry = new LocationEntry (result.getString("UUID"), result.getString("NAME"), result.getDouble("X"), result.getDouble("Y"), result.getDouble("Z"),
										result.getString("ACCESS"), result.getString("WORLD"));
				entries.add(entry);
			} while (result.next());
			
			// Checks if the list of entries has more than one element.
			// If so, prioritize the one classified under private.
			if (entries.size() > 1)
			{
				for (LocationEntry e : entries)
				{
					if (e.getAccessLevel() == WarpAccessLevel.PRIVATE)
					{
						entry = e;
						break;
					}
				}
			}
		}
		
		pstmt.close();
		c.close();
		return entry;
	}
	
	/** Fetches a location registered under a specific private or public access classifier */
	public static LocationEntry fetchLocationUnderClassifier (String uuid, String locationName, Environment env, WarpAccessLevel access) throws SQLException
	{
		locationName = locationName.toLowerCase();
		
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		ResultSet result = null;
		LocationEntry entry = null;
		
		StringBuilder query = new StringBuilder ("SELECT * FROM LOCATIONS WHERE ");
		query.append ("UUID = ? AND NAME = ? AND WORLD = ? AND ACCESS = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		pstmt.setString(2, locationName);
		pstmt.setString(3, env.name());
		pstmt.setString(4, access.name());
		
		result = pstmt.executeQuery();
		
		if (result.next())
		{
			entry = new LocationEntry (result.getString("UUID"), result.getString("NAME"), result.getDouble("X"), result.getDouble("Y"), result.getDouble("Z"),
					result.getString("ACCESS"), result.getString("WORLD"));
		}
		
		pstmt.close();
		c.close();
		return entry;
	}
	
	/** Returns a list of locations either private or public to the player as an ArrayList of LocationEntries in the player's current dimension.
	 * 		@param p - The player object
	 * 		@param level - Access specifier the player is getting their list as */
	public static ArrayList <LocationEntry> getLocationsAsList (Player p, WarpAccessLevel level) throws SQLException, RuntimeException
	{
		return getLocationsAsList (p.getUniqueId().toString(), p.getWorld().getEnvironment(), level);
	}
	
	/** Returns a list of locations either private or public to the player as an ArrayList of LocationEntries in the player's current dimension.
	 * 		@param uuid - The uuid of the player
	 * 		@param env - The dimension that the player is currently in
	 * 		@param level - Access specifier the player is getting their list as */
	public static ArrayList <LocationEntry> getLocationsAsList (String uuid, Environment env, WarpAccessLevel level) throws SQLException, RuntimeException
	{
		ResultSet result = null;
		PreparedStatement pstmt = null;
		ArrayList <LocationEntry> entries = new ArrayList <LocationEntry> ();
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ("SELECT * FROM LOCATIONS WHERE ");
		
		if (level.equals(WarpAccessLevel.PRIVATE))
		{
			query.append("UUID = ? AND WORLD = ? AND ACCESS = ?");
			pstmt = c.prepareStatement(query.toString());
			pstmt.setString(1, uuid);
			pstmt.setString(2, env.name());
			pstmt.setString(3, level.name());
		}
		else
		{
			query.append("WORLD = ? AND ACCESS = ?");
			pstmt = c.prepareStatement(query.toString());
			pstmt.setString(1, env.name());
			pstmt.setString(2, level.name());
		}
		
		result = pstmt.executeQuery();
		
		// Adds everything into an arraylist
		if (result.next())
		{
			do {
				LocationEntry entry = new LocationEntry (result.getString("UUID"), result.getString("NAME"), result.getDouble("X"), result.getDouble("Y"), result.getDouble("Z"),
						result.getString("ACCESS"), result.getString("WORLD"));
				entries.add(entry);
			} while (result.next());
		}
		
		pstmt.close();
		c.close();
		
		return entries;
	}
	
	/** Returns all locations under a specific environment
	 * 		@param env - The dimension we want to search in */
	public static ArrayList <LocationEntry> getLocationsAsList (Environment env) throws SQLException, RuntimeException
	{
		ResultSet result = null;
		PreparedStatement pstmt = null;
		ArrayList <LocationEntry> entries = new ArrayList <LocationEntry> ();
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ("SELECT * FROM LOCATIONS WHERE ");

		query.append("WORLD = ?");
		pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, env.name());
		
		result = pstmt.executeQuery();
		
		// Adds everything into an arraylist
		if (result.next())
		{
			do {
				LocationEntry entry = new LocationEntry (result.getString("UUID"), result.getString("NAME"), result.getDouble("X"), result.getDouble("Y"), result.getDouble("Z"),
						result.getString("ACCESS"), result.getString("WORLD"));
				entries.add(entry);
			} while (result.next());
		}
		
		pstmt.close();
		c.close();
		
		return entries;
	}
	
	/** Checks if a player specified location under a name already exists in the database 
	 * 	 @param uuid - UUID of the player
	 * 	@param locationName - Name of the location the player is setting their location as
	 * 	@param env - The dimension the player is in*/
	public static boolean checkIfLocationExists (String uuid, String locationName, Environment env)
	{
		try 
		{
			return fetchLocation(uuid, locationName, env) != null;
		} 
		catch (SQLException e) 
		{
			System.out.println (e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/** Checks if a player specified location under a certain access level and name already exists in the database */
	public static boolean checkIfLocationUnderClassifierExists (String uuid, String locationName, Environment env, WarpAccessLevel access)
	{
		try
		{
			return fetchLocationUnderClassifier (uuid, locationName, env, access) != null;
		}
		catch (SQLException e)
		{
			System.out.println (e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/** Attempts to fetch and return the location data that is tied under locationName from the database. 
	 * 	@param p - Player object
	 * 	@param locationName - Name of the location the player is setting their location as */
	public static Location getlocation (Player p, String locationName) throws SQLException, RuntimeException
	{
		Environment env = p.getWorld().getEnvironment();
		LocationEntry data = fetchLocation (p.getUniqueId().toString(), locationName, env);
		if (data == null)
		{
			throw new RuntimeException (locationName + " is not a registered location in " + GetCoordinates.getEnvironmentName(env));
		}
		
		double x = data.getX();
		double y = data.getY();
		double z = data.getZ();
		
		Location loc = new Location (p.getWorld(), x, y, z);
		
		return loc;
	}
}
