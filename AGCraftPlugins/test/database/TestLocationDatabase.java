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
	
	/** Tests if the player is able to add a private location with the same name as another location, but that location is public and in a different environment */
	@Test
	void sameNameTest6 ()
	{
		String player1 = "player1";
		String locName = "test1";
		
		LocationEntry oEntry = new LocationEntry (player1, locName, 1, 1, 1, PUBLIC, OVERWORLD);
		LocationEntry nEntry = new LocationEntry (player1, locName, 2, 2, 2, PRIVATE, NETHER);
		LocationEntry eEntry = new LocationEntry (player1, locName, 3, 3, 3, PUBLIC, THE_END);
		
		try
		{
			insert (oEntry);
			insert (eEntry);
			fetch (oEntry);
			fetch (eEntry);
			insert (nEntry);
			fetch (nEntry);
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests if another player is unable to add a location with the same name as a public location */
	@Test
	void sameNameTest7 ()
	{
		String locName = "test1";
		
		LocationEntry p1Entry = new LocationEntry (player (1), locName, 1, 1, 1, PUBLIC, OVERWORLD);
		LocationEntry p2Entry = new LocationEntry (player (2), locName, 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (p1Entry);
			fetch (p1Entry);
			insert (p2Entry);
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
	
	/** Similar to test 7, but this time all of their locations are private, which should be able to happen */
	@Test
	void sameNameTest8 ()
	{
		String locName = "test1";
		
		LocationEntry p1Entry = new LocationEntry (player (1), locName, 1, 1, 1, PRIVATE, OVERWORLD);
		LocationEntry p2Entry = new LocationEntry (player (2), locName, 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (p1Entry);
			fetch (p1Entry);
			insert (p2Entry);
			fetch (p2Entry);
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests if the public location can be accessed by multiple members */
	@Test
	void testPublicLocations ()
	{
		String locName = "test1";
		LocationEntry p1Entry = new LocationEntry (player(1), locName, 1, 1, 1, PUBLIC, OVERWORLD);
		
		try
		{
			insert (p1Entry);
			fetch (p1Entry);
			for (int i = 2; i < 100; ++i)
			{
				spoofedFetch (player(i), p1Entry);
			}
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests if a private location can only be accessed by the user itself */
	@Test
	void testPrivateLocations ()
	{
		String locName = "secret";
		LocationEntry p1Entry = new LocationEntry (player(1), locName, 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (p1Entry);
			fetch (p1Entry);
			
			spoofedFetch (player (99), p1Entry);
			fail ("FAIL: an exception should be thrown");
		}
		
		catch (SQLException e)
		{
			fail ("FAIL: " + e.getMessage());
		}
		
		catch (RuntimeException e)
		{
			System.out.println ("PASS: " + e.getMessage());
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
	
	/** Fetches an entry from the database spoofed as another user */
	private LocationEntry spoofedFetch (String uuid, LocationEntry entry) throws SQLException, RuntimeException
	{
		LocationEntry ret = LocationDatabaseManager.fetchLocation(uuid, entry.getLocationName(), entry.getEnvironment());
		assertEquals (ret.equals(entry), true);
		return ret;
	}
	
	/** Returns a formatted test UUID */
	private String player (int index)
	{
		return "player " + index;
	}
}
