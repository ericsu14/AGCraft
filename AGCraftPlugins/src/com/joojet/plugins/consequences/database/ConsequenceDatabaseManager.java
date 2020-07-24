package com.joojet.plugins.consequences.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import com.joojet.plugins.rewards.database.CreateRewardsDatabase;

public class ConsequenceDatabaseManager 
{
	/** Grants a new consequence to a player until a certain timestamp
	 * 		@param uuid - Player being punished
	 * 		@param timestamp - Time when the punishment expires 
	 * 		@throws SQLException - Internal SQL connection error */
	public static void punishPlayer (UUID uuid, Calendar timestamp) throws SQLException
	{
		Connection c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ();
		query.append("INSERT INTO ");
		query.append(CreateRewardsDatabase.kConsequenceDatabaseName);
		query.append(" (UUID, EXPIRE) VALUES (?,?)");
		
		c.setAutoCommit(false);
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid.toString());
		pstmt.setDate(2, new java.sql.Date(timestamp.getTimeInMillis()));
		
		pstmt.executeUpdate();
		pstmt.close();
		c.commit();
		c.close();
	}
	
	/** Retrieves all of a player's expiration dates for all of their consequences as a list
	 * 		@param uuid - UUID of the player we are looking up 
	 * 		@throws SQLException - Internal SQL connection error */
	public static ArrayList <Calendar> getPlayerTimestamps (UUID uuid) throws SQLException
	{
		ArrayList <Calendar> timestamps = new ArrayList <Calendar> ();
		Connection c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		ResultSet result;
		
		StringBuilder query = new StringBuilder ();
		query.append("SELECT EXPIRE FROM ");
		query.append(CreateRewardsDatabase.kConsequenceDatabaseName);
		query.append (" WHERE UUID = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid.toString());
		
		result = pstmt.executeQuery();
		
		if (result.next())
		{
			do {
				Calendar cal = Calendar.getInstance();
				cal.setTime(result.getDate("EXPIRE"));
				System.out.println ("Found " + cal);
				timestamps.add(cal);
			} while (result.next());
		}
		return timestamps;
	}
	
	/** Removes all consequences from a player
	 * 		@param uuid - The UUID of the player being forgiven
	 * 		@throws SQLException - Internal SQL connection error */
	public static void forgivePlayer (UUID uuid) throws SQLException
	{
		Connection c = DriverManager.getConnection(CreateRewardsDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ();
		query.append("DELETE FROM ");
		query.append(CreateRewardsDatabase.kConsequenceDatabaseName);
		query.append(" WHERE UUID = ?");
		
		c.setAutoCommit(false);
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid.toString());
		
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	
	/** Returns true if the player has active consequences
	 * 		@param uuid - The player we are looking up */
	public static boolean hasConsequences (UUID uuid)
	{
		try
		{
			ArrayList <Calendar> timestamps = getPlayerTimestamps (uuid);
			if (timestamps.isEmpty())
			{
				return false;
			}
			
			// Gets current time and compares it to all found timestamps
			Calendar curr = Calendar.getInstance();
			for (Calendar cal : timestamps)
			{
				// If any calendar timestamp does not pass current time, then we found a consequence 
				if (curr.compareTo(cal) < 0)
				{
					return true;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/** Returns the longest active consequence the player currently has
	 *  based on the current time
	 *  	@param uuid - UUID of the player we are looking for */
	public static Calendar getLongestRunningConsequence (UUID uuid)
	{
		try
		{
			ArrayList <Calendar> timestamps = getPlayerTimestamps (uuid);
			if (timestamps.isEmpty())
			{
				return null;
			}
			
			Calendar curr = Calendar.getInstance();
			Calendar longest = Calendar.getInstance();
			
			for (Calendar currVal : timestamps)
			{
				// Updates longest timestamp if one found in list surpass it.
				if (longest.compareTo(currVal) < 0)
				{
					longest = currVal;
				}
			}
			
			// Only returns the longest time if the longest timestamp is still active
			return (curr.compareTo(longest) < 0) ? longest : null;
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
