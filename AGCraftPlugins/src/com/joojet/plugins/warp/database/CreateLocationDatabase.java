package com.joojet.plugins.warp.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.joojet.biblefetcher.database.CreateDatabase;

public class CreateLocationDatabase 
{
	public static final String kDatabaseName = "locations.db";
	public static final String kDatabasePath = CreateDatabase.getDBPath(kDatabaseName);
	public static final String kLocationDatabaseName = "LOCATIONS";
	public static final String kEWarpDatabaseName = "EWARP";
	
	public static void createDatabase ()
	{
		try (Connection conn = (Connection) DriverManager.getConnection(kDatabasePath))
		{
			if (conn != null)
			{
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println ("Driver Name: " + meta.getDriverName());
				System.out.println ("Created a new database at " + kDatabasePath);
				
				initializeLocationTable();
				initializeEmergencyWarpTable();
			}
		}
		
		catch (Exception e)
		{
			System.err.println (e.getMessage());
		}
	}
	
	public static void initializeEmergencyWarpTable ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabasePath);
			stmt = c.createStatement();
			StringBuilder sql = new StringBuilder ();
			
			sql.append("CREATE TABLE ");
			sql.append(kEWarpDatabaseName);
			sql.append(" (");
			sql.append("UUID TEXT NOT NULL,");
			sql.append("COUNT INT NOT NULL");
			sql.append(")");
			
			stmt.executeUpdate(sql.toString());
			stmt.close();
			c.close();
		}
		
		catch (Exception e)
		{
			System.err.println (e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/** Initializes the location table for a newly created database */
	public static void initializeLocationTable ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabasePath);
			
			stmt = c.createStatement();
			
			StringBuilder sql = new StringBuilder ();
			sql.append("CREATE TABLE ");
			sql.append(kLocationDatabaseName);
			sql.append(" (");
			sql.append("UUID TEXT NOT NULL,");
			sql.append("NAME TEXT NOT NULL,");
			sql.append("X DOUBLE NOT NULL,");
			sql.append("Y DOUBLE NOT NULL,");
			sql.append("Z DOUBLE NOT NULL,");
			sql.append("WORLD TEXT NOT NULL,");
			sql.append("ACCESS TEXT NOT NULL");
			sql.append(")");
			
			stmt.executeUpdate(sql.toString());
			stmt.close();
			c.close();
			
			System.out.println ("Table created successfully!");
		}
		
		catch (Exception e)
		{
			System.err.println (e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/** Resets the tables in the database. Should only be used during unit testing */
	public static void dropTables ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabasePath);
			
			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS " + kLocationDatabaseName;
			stmt.executeUpdate(sql);
			stmt.executeUpdate("DROP TABLE IF EXISTS " + kEWarpDatabaseName);
			stmt.close();
			c.close();
			
			System.out.println ("Removed table");
		}
		
		catch (SQLException e)
		{
			System.err.println (e.getMessage());
		}
		
		catch (ClassNotFoundException e)
		{
			System.err.println (e.getMessage());
		}
	}
}
