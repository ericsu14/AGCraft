package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.bukkit.World.Environment;
import org.junit.jupiter.api.Test;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.CreateLocationDatabase;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.database.LocationEntry;

class TestLocationDatabase 
{
	
	public TestLocationDatabase ()
	{
		// Ensures a new database is created every time this test case is ran
		CreateLocationDatabase.createDataBase();
		resetDatabase();
	}
	
	/** Tests to see if the database could handle a basic insertion and search operation */
	@Test
	void basicInsertionTest () 
	{
		
		// Inserts single entry in the database
		LocationEntry entry = new LocationEntry ("player1", "test1", 1, 1, 1, WarpAccessLevel.PRIVATE.name(), Environment.NORMAL.name());
		
		try 
		{
			insert (entry);
			LocationEntry ret = fetch (entry);
			assertEquals (ret.equals(entry), true); 
			resetDatabase();
		} 
		catch (SQLException e) 
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Resets the test database */
	private void resetDatabase ()
	{
		CreateLocationDatabase.dropTables();
		CreateLocationDatabase.initializeTables();
	}
	
	/** Adds an entry into the database 
	 * @throws SQLException */
	private void insert (LocationEntry entry) throws SQLException
	{
		LocationDatabaseManager.insert (entry.getUUID(), entry.getLocationName(), entry.getX(), entry.getY(), entry.getZ(), entry.getEnvironment(), entry.getAccessLevel());
		assertEquals (LocationDatabaseManager.checkIfLocationExists(entry.getUUID(), entry.getLocationName(), entry.getEnvironment()), true);
	}
	
	/** Fetches an entry from the database */
	private LocationEntry fetch (LocationEntry entry) throws SQLException
	{
		return LocationDatabaseManager.fetchLocation(entry.getUUID(), entry.getLocationName(), entry.getEnvironment());
	}
}
