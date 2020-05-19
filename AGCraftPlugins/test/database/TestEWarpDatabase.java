package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.joojet.plugins.warp.database.CreateLocationDatabase;
import com.joojet.plugins.warp.database.EWarpDatabaseManager;

class TestEWarpDatabase {
	
	public TestEWarpDatabase()
	{
		resetDatabase();
	}
	
	/** Tests to see if we are able to add entries into the database*/
	@Test
	void insertionTest() 
	{
		String p1 = "user1";
		try {
			// Inserts the player into the database
			EWarpDatabaseManager.registerNewUser(p1);
			// Checks if the player's starting ticket count is accurate
			assertEquals (EWarpDatabaseManager.getTicketCount(p1),EWarpDatabaseManager.startingTickets);
		} catch (SQLException e) 
		{
			fail (e.getMessage());
		}
		
	}
	
	/** Tests to see if the "check if user exists function" is working properly */
	@Test
	void checkIfUserExistsTest ()
	{
		String p1 = "user1";
		try
		{
			// At the moment, the user should not exist
			assertEquals (EWarpDatabaseManager.checkIfUserExists(p1), false);
			// Insert the user into the DB
			EWarpDatabaseManager.registerNewUser(p1);
			assertEquals (EWarpDatabaseManager.checkIfUserExists(p1), true);
		}
		catch (SQLException e) 
		{
			fail (e.getMessage());
		}
	}
	
	/** Checks to see if incrementing the ticket count works */
	@Test
	void incrementCountTest ()
	{
		String p1 = "user1";
		try
		{
			EWarpDatabaseManager.registerNewUser(p1);
			assertEquals (EWarpDatabaseManager.getTicketCount(p1),EWarpDatabaseManager.startingTickets);
			EWarpDatabaseManager.incrementTicketCount(p1);
			assertEquals (EWarpDatabaseManager.getTicketCount(p1),EWarpDatabaseManager.startingTickets + 1);
		}
		catch (SQLException e) 
		{
			fail (e.getMessage());
		}
	}
	
	/** Checks to see if decrementing the ticket count works */
	@Test
	void decrementingCountTest ()
	{
		String p1 = "user1";
		try
		{
			EWarpDatabaseManager.registerNewUser(p1);
			assertEquals (EWarpDatabaseManager.getTicketCount(p1),EWarpDatabaseManager.startingTickets);
			EWarpDatabaseManager.decrementTicketCount(p1);
			assertEquals (EWarpDatabaseManager.getTicketCount(p1),EWarpDatabaseManager.startingTickets - 1);
		}
		catch (SQLException e) 
		{
			fail (e.getMessage());
		}
	}
	
	/** Resets the test database */
	private void resetDatabase ()
	{
		CreateLocationDatabase.dropTables();
		CreateLocationDatabase.initializeEmergencyWarpTable();
	}

}
