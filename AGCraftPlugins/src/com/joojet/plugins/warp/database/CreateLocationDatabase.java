package com.joojet.plugins.warp.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;

import com.joojet.biblefetcher.database.CreateDatabase;

public class CreateLocationDatabase 
{
	public static final String kDatabaseName = "locations.db";
	public static final String kDatabasePath = CreateDatabase.getDBPath(kDatabaseName);
	
	public static void createDataBase ()
	{
		try (Connection conn = (Connection) DriverManager.getConnection(kDatabasePath))
		{
			if (conn != null)
			{
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println ("Driver Name: " + meta.getDriverName());
				System.out.println ("Created a new database at " + kDatabasePath);
			}
		}
		
		catch (Exception e)
		{
			// TODO
		}
	}
	
	public static void initializeTables ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabasePath);
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE LOCATIONS (" +
							"UUID        TEXT  NOT NULL," +
							"NAME         TEXT  NOT NULL," +
							"X         DOUBLE   NOT NULL," +
							"Y         DOUBLE  NOT NULL,"  +
							"Z       DOUBLE   NOT NULL,"   +
							"WORLD   TEXT     NOT NULL,"   +
							"ACCESS  TEXT  NOT NULL"       +  ")";
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			
			System.out.println ("Table created successfully!");
		}
		
		catch (Exception e)
		{
			System.err.println (e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
