package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
	
	/** Tests the remove function on a single private location */
	@Test
	void testPrivateRemove ()
	{
		String locName = "temp";
		LocationEntry p1Entry = new LocationEntry (player(1), locName, 1, 1, 1, PRIVATE, OVERWORLD);
		
		try
		{
			insert (p1Entry);
			fetch (p1Entry);
			remove (p1Entry);
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests the remove function on a single public location and checks to see if that location
	 *  can no longer be accessed by other players. */
	@Test
	void testPublicRemove ()
	{
		String locName = "temp";
		LocationEntry p1Entry = new LocationEntry (player(1), locName, 1, 1, 1, PUBLIC, OVERWORLD);
		
		try
		{
			insert (p1Entry);
			fetch (p1Entry);
			
			for (int i = 2; i < 100; ++i)
			{
				assertEquals (checkIfLocationExists (player (i), p1Entry), true);
			}
			
			remove (p1Entry);
			
			for (int i = 2; i < 100; ++i)
			{
				assertEquals (checkIfLocationExists (player (i), p1Entry), false);
			}
		}
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests if the remove function does not remove locations with the same name, but different environments */
	@Test
	void testRemoveMultiEnv ()
	{
		String locName = "temp";
		String player1 = player(1);
		
		LocationEntry oEntry = new LocationEntry (player1, locName, 1, 1, 1, PRIVATE, OVERWORLD);
		LocationEntry nEntry = new LocationEntry (player1, locName, 1, 1, 1, PRIVATE, NETHER);
		LocationEntry eEntry = new LocationEntry (player1, locName, 1, 1, 1, PRIVATE, THE_END);
		
		try
		{
			insert (oEntry);
			insert (nEntry);
			insert (eEntry);
			
			remove (nEntry);
			assertEquals (checkIfLocationExists (player1, oEntry), true);
			assertEquals (checkIfLocationExists (player1, nEntry), false);
			assertEquals (checkIfLocationExists (player1, eEntry), true);
			
			remove (oEntry);
			assertEquals (checkIfLocationExists (player1, oEntry), false);
			assertEquals (checkIfLocationExists (player1, nEntry), false);
			assertEquals (checkIfLocationExists (player1, eEntry), true);
			
			remove (eEntry);
			assertEquals (checkIfLocationExists (player1, oEntry), false);
			assertEquals (checkIfLocationExists (player1, nEntry), false);
			assertEquals (checkIfLocationExists (player1, eEntry), false);
			
			insert (oEntry);
			assertEquals (checkIfLocationExists (player1, oEntry), true);
			assertEquals (checkIfLocationExists (player1, nEntry), false);
			assertEquals (checkIfLocationExists (player1, eEntry), false);
		}
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests if the getLocationsAsList function is working properly on private entries */
	@Test
	void testListofLocationsPrivate ()
	{
		try
		{
			int numLocationsOverworld = 100, numLocationsNether = 50, numLocationsEnd = 110, numLocationsOverworldP2 = 10;
			LocationEntry entryOverworld = createRandomPrivateLocations (numLocationsOverworld, 1, Environment.NORMAL);
			LocationEntry entryNether = createRandomPrivateLocations (numLocationsNether, 1, Environment.NETHER);
			LocationEntry entryEnd = createRandomPrivateLocations (numLocationsEnd, 1, Environment.THE_END);
			LocationEntry entryOverworldP2 = createRandomPrivateLocations (numLocationsOverworldP2, 2, Environment.NORMAL);
			
			ArrayList <LocationEntry> entriesOverworld = this.getListofLocations(entryOverworld);
			assertEquals (entriesOverworld.size(), numLocationsOverworld);
			
			ArrayList <LocationEntry> entriesNether= this.getListofLocations(entryNether);
			assertEquals (entriesNether.size(), numLocationsNether);
			
			ArrayList <LocationEntry> entriesEnd = this.getListofLocations(entryEnd);
			assertEquals (entriesEnd.size(), numLocationsEnd);
			
			ArrayList <LocationEntry> entriesOverworldP2 = this.getListofLocations(entryOverworldP2);
			assertEquals (entriesOverworldP2.size(), numLocationsOverworldP2);
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests if the getLocationsAsList function is working properly on public entries */
	@Test
	void testListofLocationsPublic ()
	{
		try
		{
			int numLocationsOverworld = 100, numLocationsNether = 50, numLocationsEnd = 110;
			LocationEntry entryOverworld = createRandomPublicLocations (numLocationsOverworld, Environment.NORMAL);
			LocationEntry entryNether = createRandomPublicLocations (numLocationsNether, Environment.NETHER);
			LocationEntry entryEnd = createRandomPublicLocations (numLocationsEnd, Environment.THE_END);
			
			ArrayList <LocationEntry> entriesOverworld = this.getListofLocations(entryOverworld);
			assertEquals (entriesOverworld.size(), numLocationsOverworld);
			
			ArrayList <LocationEntry> entriesNether= this.getListofLocations(entryNether);
			assertEquals (entriesNether.size(), numLocationsNether);
			
			ArrayList <LocationEntry> entriesEnd = this.getListofLocations(entryEnd);
			assertEquals (entriesEnd.size(), numLocationsEnd);
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Tests this following edge case:
	 * 		p1 sets a private entry
	 * 		p2 registers a public entry under the same name as p1's private entry
	 * 	... check to see if p1 still gets his private location after calling fetch. */
	@Test
	void sameNameTest9 ()
	{
		String locName = "spawn";
		LocationEntry p1PrivateEntry = new LocationEntry (player(1), locName, 1, 1, 1, OVERWORLD, PRIVATE);
		LocationEntry p2PublicEntry = new LocationEntry (player(21), locName, 1, 1, 1, OVERWORLD, PUBLIC);
		
		try
		{
			insert (p1PrivateEntry);
			insert (p2PublicEntry);
			
			LocationEntry entry = fetch (p1PrivateEntry);
			assertEquals (entry, p1PrivateEntry);
		}
		
		catch (Exception e)
		{
			fail ("FAIL: " + e.getMessage());
		}
	}
	
	/** Populates the database with a set number of private locations 
	 * @throws SQLException, RuntimeException */
	private LocationEntry createRandomPrivateLocations (int number, int player, Environment env) throws SQLException, RuntimeException
	{
		LocationEntry entry = null;
		for (int i = 0; i < number; ++i)
		{
			entry = new LocationEntry (player(player), generateRandomString() , 1, 1, 1, PRIVATE, env.name());
			insert (entry);
		}
		
		return entry;
	}
	
	/** Populates the database with a number of public locations  */
	private LocationEntry createRandomPublicLocations (int number, Environment env) throws SQLException, RuntimeException
	{
		LocationEntry entry = null;
		for (int i = 0; i < number; ++i)
		{
			entry = new LocationEntry (player(1000+i), generateRandomString() , 1, 1, 1, PUBLIC, env.name());
			insert (entry);
		}
		return entry;
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
	
	/** Removes an entry from the database and tests if that entry no longer exists */
	private void remove (LocationEntry entry) throws SQLException
	{
		LocationDatabaseManager.removeLocation(entry.getUUID(), entry.getLocationName(), entry.getEnvironment(), entry.getAccessLevel());
		assertEquals (LocationDatabaseManager.fetchLocation(entry.getUUID(), entry.getLocationName(), entry.getEnvironment()), null);
	}
	
	/** Fetches an entry from the database spoofed as another user */
	private LocationEntry spoofedFetch (String uuid, LocationEntry entry) throws SQLException, RuntimeException
	{
		LocationEntry ret = LocationDatabaseManager.fetchLocation(uuid, entry.getLocationName(), entry.getEnvironment());
		assertEquals (ret.equals(entry), true);
		return ret;
	}
	
	/** Checks if a location exists in the database. */
	private boolean checkIfLocationExists (String uuid, LocationEntry entry) throws SQLException, RuntimeException
	{
		return LocationDatabaseManager.checkIfLocationExists(uuid, entry.getLocationName(), entry.getEnvironment());
	}
	
	/** Returns a list of locations available to the player */
	private ArrayList <LocationEntry> getListofLocations (LocationEntry entry) throws SQLException, RuntimeException
	{
		return LocationDatabaseManager.getLocationsAsList(entry.getUUID(), entry.getEnvironment(), entry.getAccessLevel());
	}
	
	/** Returns a formatted test UUID */
	private String player (int index)
	{
		return "player " + index;
	}
	
	private String generateRandomString ()
	{
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}
}
