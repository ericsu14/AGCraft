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
	/** Enum Constants */
	private final String PUBLIC = WarpAccessLevel.PUBLIC.name();
	private final String PRIVATE = WarpAccessLevel.PRIVATE.name();
	private final String OVERWORLD = Environment.NORMAL.name();
	private final String NETHER = Environment.NETHER.name();
	private final String THE_END = Environment.THE_END.name();
	
	public TestLocationDatabase ()
	{
		// Ensures a new database is created every time this test case is ran
		// CreateLocationDatabase.createDataBase();
		resetDatabase();
	}
	
	/** Tests to see if the database could handle a basic insertion and search operation */
	@Test
	void basicInsertionTest () 
	{
		// Inserts single, private location entry in the database
		LocationEntry entry = new LocationEntry ("player1", "test1", 1, 1, 1, PRIVATE, OVERWORLD);
		
		try 
		{
			insert (entry);
			fetch (entry);
		} 
		catch (SQLException e) 
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests to see if the player is prevented from entering a private location with the same name twice */
	@Test
	void sameNameTest ()
	{
		// Inserts single, private location entry in the database
		LocationEntry entry = new LocationEntry ("player1", "test1", 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (entry);
			insert (entry);
			fail ("FAIL: an exception should be thrown");
		}
		
		catch (SQLException e)
		{
			fail ("FAIL: " + e.getMessage());
		}
		
		catch (RuntimeException e)
		{
			System.out.println (e.getMessage());
			assertEquals (true, true);
		}
	}
	
	/** Tests to see if a player prevented on entering a private location with the same name as a location that is already inserted publicly */
	@Test
	void sameNameTest2 ()
	{
		String locName = "test1";
		LocationEntry publicEntry = new LocationEntry ("player1", locName, 1, 1, 1, PUBLIC, OVERWORLD);
		LocationEntry privateEntry = new LocationEntry ("player1", locName, 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (publicEntry);
			insert (privateEntry);
			fail ("FAIL: an exception should be thrown");
		}
		
		catch (SQLException e)
		{
			fail ("FAIL: " + e.getMessage());
		}
		
		catch (RuntimeException e)
		{
			System.out.println (e.getMessage());
		}
	}
	
	/** Tests to see if a player prevented on entering a private location with the same name as a location that is already inserted privately */
	@Test
	void sameNameTest3 ()
	{
		String locName = "test1";
		LocationEntry publicEntry = new LocationEntry ("player1", locName, 1, 1, 1, PUBLIC, OVERWORLD);
		LocationEntry privateEntry = new LocationEntry ("player1", locName, 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (privateEntry);
			insert (publicEntry);
			fail ("FAIL: an exception should be thrown");
		}
		
		catch (SQLException e)
		{
			fail ("FAIL: " + e.getMessage());
		}
		
		catch (RuntimeException e)
		{
			System.out.println (e.getMessage());
		}
	}
	
	/** Tests to see if a player can insert a location with the same name across multiple environments. The player should be able to do so. */
	@Test
	void sameNameTest4 ()
	{
		String player1 = "player1";
		String locName = "test1";
		
		LocationEntry oEntry = new LocationEntry (player1, locName, 1, 1, 1, PRIVATE, OVERWORLD);
		LocationEntry nEntry = new LocationEntry (player1, locName, 1, 1, 1, PRIVATE, NETHER);
		LocationEntry eEntry = new LocationEntry (player1, locName, 1, 1, 1, PRIVATE, THE_END);
		
		try
		{
			insert (oEntry);
			insert (nEntry);
			insert (eEntry);
			fetch (oEntry);
			fetch (nEntry);
			fetch (eEntry);
		}
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Similar to sameNameTest4, but in the public sphere */
	@Test
	void sameNameTest5 ()
	{
		String player1 = "player1";
		String locName = "test1";
		
		LocationEntry oEntry = new LocationEntry (player1, locName, 1, 1, 1, PUBLIC, OVERWORLD);
		LocationEntry nEntry = new LocationEntry (player1, locName, 1, 1, 1, PUBLIC, NETHER);
		LocationEntry eEntry = new LocationEntry (player1, locName, 1, 1, 1, PUBLIC, THE_END);
		
		try
		{
			insert (oEntry);
			insert (nEntry);
			insert (eEntry);
			fetch (oEntry);
			fetch (nEntry);
			fetch (eEntry);
		}
		catch (Exception e)
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
	
	/** Fetches an entry from the database and tests if the fetched entry is equal to the one logged here */
	private LocationEntry fetch (LocationEntry entry) throws SQLException
	{
		LocationEntry ret = LocationDatabaseManager.fetchLocation(entry.getUUID(), entry.getLocationName(), entry.getEnvironment());
		assertEquals (ret.equals(entry), true);
		return ret;
	}
}
