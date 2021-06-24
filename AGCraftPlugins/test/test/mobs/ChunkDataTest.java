package test.mobs;

import java.util.HashSet;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.junit.Test;
import org.mockito.Mockito;

import com.joojet.plugins.mobs.chunk.ChunkData;
import com.joojet.plugins.warp.commands.tasks.AsyncChunkLoaderTask;

public class ChunkDataTest 
{
	/** Mock overworld instance used for testing */
	protected World overworld;
	/** Mock nether instance used for testing */
	protected World nether;
	
	public ChunkDataTest ()
	{
		overworld = Mockito.mock(World.class);
		Mockito.when(overworld.getEnvironment()).thenReturn(Environment.NORMAL);
		nether = Mockito.mock(World.class);
		Mockito.when(nether.getEnvironment()).thenReturn(Environment.NETHER);
	}
	
	/** Tests if the equals function for ChunkData can correctly identify differing ChunkData instances
	 *  with the same chunk-coordinates and world as equal. */
	@Test
	public void testChunkDataEquals ()
	{	
		ChunkData o1 = new ChunkData (1, 1, this.overworld, 0);
		ChunkData o2 = new ChunkData (1, 1, this.overworld, 1);
		assert (o1.equals(o2));
		
		ChunkData n1 = new ChunkData (1, 1, this.nether, 13);
		assert (!o1.equals(n1));
		assert (!o2.equals(n1));
		
		ChunkData o3 = new ChunkData (3000, -300, this.overworld, 1);
		ChunkData o4 = new ChunkData (3000, -300, this.overworld, 12);
		assert (o3.equals(o4));
	}
	
	/** Tests the hashcode function for the ChunkData class */
	@Test
	public void testChunkDataHashCode ()
	{
		ChunkData o1 = new ChunkData (1, 1, this.overworld, 0);
		ChunkData o2 = new ChunkData (1, 1, this.overworld, 1);
		assert (o1.hashCode() == o2.hashCode());
		
		ChunkData n1 = new ChunkData (1, 1, this.nether, 69);
		assert (o1.hashCode() != n1.hashCode());
		assert (o2.hashCode() != n1.hashCode());
		
		HashSet <ChunkData> testSet = new HashSet <ChunkData> ();
		testSet.add(o1);
		assert (testSet.contains(o2));
		testSet.add(o2);
		assert (!testSet.contains(n1));
		testSet.add(n1);
		assert (testSet.size() == 2);
	}
	
	@Test
	public void testGetNearbyChunksFunction ()
	{
		AsyncChunkLoaderTask chunkLoaderTask = new AsyncChunkLoaderTask (null, "foo", null, 32);
		HashSet <ChunkData> testData1 = chunkLoaderTask.getNearbyChunks(-3000, 3000, overworld);
		int expectedSize = testData1.size();
		HashSet <ChunkData> testData2 = chunkLoaderTask.getNearbyChunks(-3000, -3000, overworld);
		assert (testData2.size() == expectedSize);
		
		HashSet <ChunkData> testData3 = chunkLoaderTask.getNearbyChunks(3000, 3000, overworld);
		assert (testData3.size() == expectedSize);

		HashSet <ChunkData> testData4 = chunkLoaderTask.getNearbyChunks(-3000, 3000, overworld);
		assert (testData4.size() == expectedSize);
		
		HashSet <ChunkData> testData5 = chunkLoaderTask.getNearbyChunks(100, 3000, overworld);
		assert (testData5.size() == expectedSize);
		
		HashSet <ChunkData> testData6 = chunkLoaderTask.getNearbyChunks(100 , 100, overworld);
		assert (testData6.size() == expectedSize);
	}
}
