package mobs;

import org.bukkit.entity.EntityType;
import org.junit.Test;

import mobs.test_mobs.IgnoreNonFactionedTestMob;
import mobs.test_mobs.SimpleNeutralTestMob;
import mobs.test_mobs.SimpleUCLATestMob;
import mobs.test_mobs.SimpleUSCTestMob;
import mobs.test_mobs.SimpleZombieDestroyer;

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
		assert (!usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.SKELETON, ucla1));
		assert (!usc2.isAlliesOf(EntityType.SKELETON, EntityType.ZOMBIE, ucla2));
		assert (!ucla1.isAlliesOf(EntityType.CAVE_SPIDER, EntityType.SPIDER, usc1));
		assert (!usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.CAVE_SPIDER, ucla1));
	}
	
	/** Tests to see that mobs will not target entities who are in their hit-list, but are also in the
	 *  same faction as the other mob */
	@Test
	public void testAlliedHitListBetrayal ()
	{
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.SKELETON, usc2));
		assert (ucla1.isAlliesOf(EntityType.SKELETON, EntityType.ZOMBIE, ucla2));
	}
	
	/** Tests to see if factioned and non-factioned mobs will target neutral mobs whose EntityTypes are in their hitlist
	 *  and vice versa */
	@Test
	public void testHitlistedNeutralMob ()
	{
		SimpleNeutralTestMob neutralZombie = new SimpleNeutralTestMob ();
		assert (!usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, neutralZombie));
		assert (!neutralZombie.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, usc1));
		assert (!ucla1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, neutralZombie));
		assert (!neutralZombie.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, ucla1));
		
		SimpleZombieDestroyer neutralZombieDestroyer = new SimpleZombieDestroyer ();
		assert (neutralZombieDestroyer.getHitList().contains(EntityType.ZOMBIE));
		assert (!neutralZombieDestroyer.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, neutralZombie));
		assert (!neutralZombie.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, neutralZombieDestroyer));
	}
	
	/** Tests to see if allied factioned mobs (custom mobs who ignore targeting the player)
	 *  are allies with neutral mobs whose EntityTypes are not in their hitlist without the
	 *  IGNORE_NON_FACTIONED_ENTITES enabled */
	@Test
	public void testAlliedNonHitlistedNeutralMob ()
	{
		SimpleNeutralTestMob neutralVillager = new SimpleNeutralTestMob ();
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.VILLAGER, neutralVillager));
		assert (neutralVillager.isAlliesOf(EntityType.VILLAGER, EntityType.ZOMBIE, usc1));
	}
	
	/** Tests to see if non-allied factioned mobs are enemies with neutral mobs whose EntityTypes are not in their
	 *  hitlist without the IGNORE_NON_FACTIONED_ENTITES flag enabled */
	@Test
	public void testNonAlliedNonHistlistedNeturalMob ()
	{
		SimpleNeutralTestMob neutralVillager = new SimpleNeutralTestMob ();
		assert (!ucla1.isAlliesOf(EntityType.ZOMBIE, EntityType.VILLAGER, neutralVillager));
		assert (!ucla2.isAlliesOf(EntityType.VILLAGER, EntityType.VILLAGER, neutralVillager));
	}
	
	/** Tests to see if the IGNORE_NON_FACTIONED_ENTITIES works as intended on recognizing non-factioned and factioned entities.
	 *  This test entity is set to be rivals of both USC and UCLA mobs, in which should force both USC and UCLA factions to see
	 *  it as a threat even though they are not set to rival each other initially. */
	@Test
	public void testIgnoreNonFactionedEntitiesFlag ()
	{
		IgnoreNonFactionedTestMob stanfordMob = new IgnoreNonFactionedTestMob ();
		SimpleNeutralTestMob neutralVillager = new SimpleNeutralTestMob ();
		assert (stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.VILLAGER, neutralVillager));
		assert (!stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, usc1));
		assert (!stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, ucla1));
		assert (!usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, stanfordMob));
		assert (!ucla1.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, stanfordMob));
	}
	
	/** Tests to see if factioned mobs target hitlisted mobs who do not have a MobEquipment instance */
	@Test
	public void testHitlistedVanillaMobs ()
	{
		assert (!usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.SKELETON, null));
		assert (!ucla1.isAlliesOf(EntityType.SKELETON, EntityType.ZOMBIE, null));
	}
	
	/** Tests to see if factioned mobs do not target non-hitlisted mobs who do not have a MobEquipment instance */
	@Test
	public void testNonHitlistedVanillaMobs ()
	{
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.VILLAGER, null));
		assert (ucla1.isAlliesOf(EntityType.SKELETON, EntityType.AXOLOTL, null));
	}
	
	/** Tests to see if factioned mobs does not target non-factioned mobs who are in its ignore list */
	@Test
	public void testIgnoredNonFactionedMobs ()
	{
		// Vanilla mob test
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.IRON_GOLEM, null));
		// Neutral mob test
		SimpleNeutralTestMob neutralIronGolem = new SimpleNeutralTestMob ();
		neutralIronGolem.addTargetsToHitList(EntityType.ZOMBIE);
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.IRON_GOLEM, neutralIronGolem));
		assert (neutralIronGolem.isAlliesOf(EntityType.IRON_GOLEM, EntityType.ZOMBIE, usc1));
		
	}
	
	/** Tests to see if factioned mobs do not targed factioned mobs who are in its ignore list, regardless if they are rivals
	 *  or not */
	@Test
	public void testIgnoredFactionedMobs ()
	{
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.IRON_GOLEM, ucla1));
		assert (ucla1.isAlliesOf(EntityType.IRON_GOLEM, EntityType.ZOMBIE, usc1));
		
		IgnoreNonFactionedTestMob stanfordGolem = new IgnoreNonFactionedTestMob ();
		assert (stanfordGolem.isAlliesOf(EntityType.IRON_GOLEM, EntityType.ZOMBIE, usc1));
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.IRON_GOLEM, stanfordGolem));
	}
	
	/** Tests to see if allied mobs who have the player in its ignore list are not hostile to the player */
	@Test
	public void alliedIgnorePlayerTest ()
	{
		assert (usc1.isAlliesOf(EntityType.ZOMBIE, EntityType.PLAYER, null));
		assert (usc2.isAlliesOf(EntityType.ZOMBIE, EntityType.PLAYER, null));
	}
	
	/** Tests to see if non-allied mobs who do not have the player in their hitlist nor ignore list are neutral to the player
	 *  (neutral meaning that it uses their natural MobGoals for hunting) */
	@Test
	public void nonAlliedNeutralPlayerTest ()
	{
		assert (!ucla1.isAlliesOf(EntityType.ZOMBIE, EntityType.PLAYER, null));
		assert (!ucla2.isAlliesOf(EntityType.SKELETON, EntityType.PLAYER, null));
	}
	
	/** Tests to see if factioned mobs with the IGNORE_NON_FACTIONED_ENTITIES flag enabled will ignore entities who are apart
	 *  of their hitlist, but not in a faction */
	@Test
	public void testIgnoreNonFactionedFlagOnNeutralEntities ()
	{
		IgnoreNonFactionedTestMob stanfordMob = new IgnoreNonFactionedTestMob ();
		// Tests to see if non-factioned custom zombies are ignored by the stanford mob
		SimpleNeutralTestMob neutralMob = new SimpleNeutralTestMob ();
		assert (stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, neutralMob));
		assert (neutralMob.isAlliesOf(EntityType.ZOMBIE, EntityType.ZOMBIE, stanfordMob));
		
		// Test to see if non-factioned custom spiders, who are not apart of its hitlist are also ignored
		assert (stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.SPIDER, neutralMob));
		assert (neutralMob.isAlliesOf(EntityType.SPIDER, EntityType.ZOMBIE, stanfordMob));
		
		// Test vanilla mobs with the same procedures
		assert (stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.SKELETON, null));
		assert (stanfordMob.isAlliesOf(EntityType.SKELETON, EntityType.CAVE_SPIDER, null));
	}
	
	/** Tests to see if factioned mobs with the IGNORE_NON_FACTIONED_ENTITIES flag enabled will still target players, since they cannot
	 *  be apart of any faction. */
	@Test
	public void testIgnoreNonFactionedFlagOnPlayers ()
	{
		IgnoreNonFactionedTestMob stanfordMob = new IgnoreNonFactionedTestMob ();
		assert (!stanfordMob.isAlliesOf(EntityType.ZOMBIE, EntityType.PLAYER, null));
	}
}
