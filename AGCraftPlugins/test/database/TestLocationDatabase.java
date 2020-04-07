package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.World.Environment;
import org.junit.jupiter.api.Test;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.CreateLocationDatabase;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.database.LocationEntry;

class TestLocationDatabase 
{
	// Test UUID for main player
	public final String testPlayerID = "player1";
	public TestLocationDatabase ()
	{
		CreateLocationDatabase.createDataBase();
		CreateLocationDatabase.dropTables();
		CreateLocationDatabase.initializeTables();
	}
	
	/** Tests to see if the database could handle a basic insertion and search operation */
	@Test
	void basicInsertionTest () 
	{
		
		// Inserts a 
		double x = 1, y = 1, z = 1;
		Environment env = Environment.NORMAL;
		String locName = "test1";
		WarpAccessLevel level = WarpAccessLevel.PRIVATE;
		
		try 
		{
			LocationDatabaseManager.insert(this.testPlayerID, locName, x, y, z, env, level);
			assertEquals (LocationDatabaseManager.checkIfLocationExists(this.testPlayerID, locName, env), true);
			LocationEntry entry = LocationDatabaseManager.fetchLocation(this.testPlayerID, locName, env);
			assertEquals (entry.getLocationName(), locName);
			
		} catch (SQLException e) 
		{
			fail ("FAIL: " + e.getMessage());
		}
	}

}
