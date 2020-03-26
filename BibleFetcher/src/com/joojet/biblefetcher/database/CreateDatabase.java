package com.joojet.biblefetcher.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase 
{
	public final static String kDatabaseName = "bibles";
	public final static String kDatabaseURL = "jdbc:sqlite:.\\" + kDatabaseName;
	
	/** Creates a new database in ./database/bibles.
	 * 	WARNING: Running this code will overwrite everything in the old database! */
	public static void createNewDatabase ()
	{
		try (Connection conn = DriverManager.getConnection(kDatabaseURL))
		{
			if (conn != null)
			{
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println ("Driver Name: " + meta.getDriverName());
				System.out.println ("Created a new database");
			}
			
			initializeTables();
		}
		
		catch (SQLException e)
		{
			System.out.println ("Error: " + e.getMessage());
		}
	}
	
	/** Creates a singular table allowing this software to quickly look up stored bible chapters. */
	private static void initializeTables ()
	{
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(kDatabaseURL);
			
			System.out.println ("Opened database!");
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE BIBLES (" +
							"BIBLE_ID        TEXT  NOT NULL," +
							"BOOK_ID         TEXT  NOT NULL," +
							"CHAPTER         INT   NOT NULL," +
							"CONTENT         TEXT  NOT NULL)";
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
	
	public static void main (String [] args)
	{
		CreateDatabase.createNewDatabase();
	}

}
