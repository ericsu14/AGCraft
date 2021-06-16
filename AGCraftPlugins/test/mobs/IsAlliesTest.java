package mobs;
import org.bukkit.entity.EntityType;
import org.junit.Test;

import mobs.test_mobs.SimpleUCLATestMob;
import mobs.test_mobs.SimpleUSCTestMob;

public class IsAlliesTest 
{
	protected SimpleUSCTestMob usc1 = new SimpleUSCTestMob ();
	protected SimpleUSCTestMob usc2 = new SimpleUSCTestMob ();
	protected SimpleUCLATestMob ucla1 = new SimpleUCLATestMob ();
	protected SimpleUCLATestMob ucla2 = new SimpleUCLATestMob ();
	
	/** Tests to see if multiple factioned monsters of the same MobEquipment instance are allies with each other */
	@Test
	public void testSimpleMobAllies ()
	{	
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, usc2));
		assert (ucla1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, ucla2));
	}
	
	/** Tests to see if the USC and UCLA mobs are rivals with each other */
	@Test
	public void testSimpleMobEnemies ()
	{
		assert (!ucla1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, usc1));
		assert (!ucla2.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, usc2));
	}
	
	/** Tests to see that mobs will not target entities who are in their hit-list, but are also in the
	 *  same faction as the other mob */
	@Test
	public void testAlliedHitListBetrayal ()
	{
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.SKELETON, usc2));
		assert (ucla1.isAlliesOf(EntityType.SKELETON, EntityType.ZOMBIE, ucla2));
	}
}
