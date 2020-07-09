package com.joojet.plugins.warp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EWarpDatabaseManager 
{
	// Starting amount of "get out of jail free" passes each player starts with
	public static int startingTickets = 5;
	
	/** Registers a new player into this database with a starting count of (n)
	 *  get out of jail free tickets 
	 *  @throws SQLException */
	public static void registerNewUser (String uuid) throws SQLException
	{
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder ();
		query.append("INSERT INTO ");
		query.append(CreateLocationDatabase.kEWarpDatabaseName);
		query.append(" (UUID, COUNT) ");
		query.append("VALUES (?, ?)");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		pstmt.setInt(2, startingTickets);
		pstmt.executeUpdate();
		pstmt.close ();
		// c.commit();
		c.close();
	}
	
	/** Decrements the amount of tickets the user has */
	public static void decrementTicketCount (String uuid) throws SQLException
	{
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(CreateLocationDatabase.kEWarpDatabaseName);
		query.append(" SET COUNT = COUNT - 1 ");
		query.append("WHERE UUID = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		pstmt.executeUpdate();
		pstmt.close ();
		// c.commit();
		c.close();
	}
	
	/** Increments the amount of tickets the user has */
	public static void incrementTicketCount (String uuid) throws SQLException
	{
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(CreateLocationDatabase.kEWarpDatabaseName);
		query.append(" SET COUNT = COUNT + 1 ");
		query.append("WHERE UUID = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		pstmt.executeUpdate();
		pstmt.close ();
		// c.commit();
		c.close();
	}
 	
	/** Returns the total number of get out of free tickets the user has 
	 *  @throws SQLException */
	public static int getTicketCount (String uuid) throws SQLException
	{
		int count = -1;
		ResultSet set;
		Connection c = DriverManager.getConnection(CreateLocationDatabase.kDatabasePath);
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ");
		query.append(CreateLocationDatabase.kEWarpDatabaseName);
		query.append(" WHERE UUID = ?");
		
		PreparedStatement pstmt = c.prepareStatement(query.toString());
		pstmt.setString(1, uuid);
		set = pstmt.executeQuery();
		
		if (set.next())
		{
			count = set.getInt("COUNT");
		}
		
		pstmt.close ();
		c.close();
		
		return count;
	}
	
	/** Returns true if the user does not exist in the database */
	public static boolean checkIfUserExists (String uuid)
	{
		try 
		{
			return getTicketCount(uuid) >= 0;
		} 
		catch (SQLException e)
		{
			System.out.println (e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
