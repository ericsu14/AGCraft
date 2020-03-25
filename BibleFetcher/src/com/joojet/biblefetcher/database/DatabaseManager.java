package com.joojet.biblefetcher.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;

public class DatabaseManager 
{
	
	/** Caches new bible content into the database */
	public static void insert (BibleID translation, BookID book, int chapter, String content)
	{
		Connection c = null;
		
		try {
			c = DriverManager.getConnection(CreateDatabase.kDatabaseURL);
			c.setAutoCommit(false);

			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO BIBLES (BIBLE_ID,BOOK_ID,CHAPTER,CONTENT) VALUES(?,?,?,?)");
			
			PreparedStatement pstmt = c.prepareStatement(query.toString());
			pstmt.setString(1, translation.getBibleID());
			pstmt.setString(2, book.getID());
			pstmt.setInt(3, chapter);
			pstmt.setString(4, content);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			c.commit();
			c.close();
		}
		
		catch (Exception e)
		{
			System.err.println ("Error: " + e.getClass().getName() + ": " + e.getMessage());
			System.err.println ("Attempting to create a new database...");
			CreateDatabase.createNewDatabase();
		}
	}
	
	/** Retrieves bible content from database */
	public static String fetch (BibleID translation, BookID book, int chapter)
	{
		StringBuilder result = new StringBuilder();
		
		Connection c = null;
		
		try 
		{
			c = DriverManager.getConnection(CreateDatabase.kDatabaseURL);
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM BIBLES WHERE BIBLE_ID=? AND BOOK_ID=? AND CHAPTER=?");
			PreparedStatement pstmt = c.prepareStatement(query.toString());
			pstmt.setString(1, translation.getBibleID());
			pstmt.setString(2, book.getID());
			pstmt.setInt(3, chapter);
			
			ResultSet rs = pstmt.executeQuery();
			
			result.append(rs.getString("CONTENT"));
			pstmt.close();
			c.close();
		}
		
		catch (Exception e)
		{
			System.err.println (translation.getBibleID() + " | " + book.getID() + " | " + chapter + " does not exist in the database. Fetching content from internet...");
		}
		
		return result.toString();
	}
}
