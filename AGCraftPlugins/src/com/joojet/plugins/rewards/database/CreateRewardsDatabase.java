package com.joojet.plugins.rewards.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.joojet.biblefetcher.database.CreateDatabase;

public class CreateRewardsDatabase 
{
	public static final String kDatabaseName = "rewards.db";
	public static final String kDatabasePath = CreateDatabase.getDBPath(kDatabaseName);
	public static final String kRewardsDatabaseName = "REWARDS";
	public static final String kConsequenceDatabaseName = "CONSEQUENCES";
	
	public static void createDatabase ()
	{		
		try (Connection conn = (Connection) DriverManager.getConnection(kDatabasePath))
		{
			if (conn != null)
			{
				createRewardsDatabase();
				createConsequencesDatabase();
			}
			else
			{
				System.err.println ("Connection invalid");
			}
		}
		
		catch (Exception e)
		{
			System.err.println (e.getMessage());
		}
	}
	
	public static void createRewardsDatabase ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabasePath);
			stmt = c.createStatement();
			StringBuilder sql = new StringBuilder ();
			
			sql.append("CREATE TABLE IF NOT EXISTS ");
			sql.append(kRewardsDatabaseName);
			sql.append(" (");
			sql.append("REWARD_ID INTEGER PRIMARY KEY,");
			sql.append("UUID TEXT NOT NULL,");
			sql.append("REWARD TEXT NOT NULL,");
			sql.append("EVENT TEXT NOT NULL,");
			sql.append("CLAIMED BOOLEAN NOT NULL");
			sql.append(");");
			
			stmt.executeUpdate(sql.toString());
			stmt.close();
			c.close();
		}
		
		catch (Exception e)
		{
			System.err.println (e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void createConsequencesDatabase ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabasePath);
			stmt = c.createStatement();
			StringBuilder sql = new StringBuilder ();
			
			sql.append("CREATE TABLE IF NOT EXISTS ");
			sql.append(kConsequenceDatabaseName);
			sql.append(" (");
			sql.append("CONSEQUENCE_ID INTEGER PRIMARY KEY,");
			sql.append("UUID TEXT NOT NULL,");
			sql.append("EXPIRE DATE NOT NULL,");
			sql.append(");");
			
			stmt.executeUpdate(sql.toString());
			stmt.close();
			c.close();
		}
		
		catch (Exception e)
		{
			System.err.println (e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
