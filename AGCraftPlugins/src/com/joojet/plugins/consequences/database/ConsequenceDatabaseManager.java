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
	 * 		@throws SQLException - Internal SQL error */
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
	
	/** Retrieves all of a player's expiration dates for all of their consequences as a list */
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
}
