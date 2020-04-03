package com.joojet.biblefetcher.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.joojet.biblefetcher.api.APIKeyReader;

public class CreateDatabase 
{
	public final static String kDirectoryPath = "plugins\\AGCraft";
	public final static String kDatabaseName = "bibles.db";
	public final static String kDatabaseURL = "jdbc:sqlite:.\\" + kDirectoryPath + "\\" + kDatabaseName;
	
	/** Attempts to create a new database in ./plugins/AGCraft/bibles.*/
	public static void createNewDatabase ()
	{
		boolean mkDir = createDirectory();
		
		try (Connection conn = DriverManager.getConnection(kDatabaseURL))
		{
			/* Check for the existence of a plugin configuration file. If not, create one and prompt the server
			 * admin to provide needed API keys. */
			if (!APIKeyReader.checkConfigFile())
			{
				APIKeyReader.createConfigFile();
			}
			
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
		
		catch (IOException e)
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
	
	/** Attempts to create a new directory under the directory path constant.
	 *  Returns true if successful. */
	private static boolean createDirectory ()
	{
		return new File (kDirectoryPath).mkdirs();
	}
	
	public static void main (String [] args)
	{
		CreateDatabase.createNewDatabase();
	}

}
