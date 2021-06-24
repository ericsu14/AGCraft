package test.mobs;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.junit.Test;
import org.mockito.Mockito;

import com.joojet.plugins.mobs.CustomSkillsListener;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.damage.entities.DamageDisplayEntity;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;

import test.mobs.test_mobs.SimpleNeutralTestMob;
import test.mobs.test_mobs.SimpleUCLATestMob;
import test.mobs.test_mobs.SimpleUSCTestMob;

public class MobFilterTest 
{
	/** Mob interpreter instance used for storing and looking up MobEquipment instances used for testing */
	protected MonsterTypeInterpreter mobClasses;
	/** Summoning scroll interpreter used as a dependency for the TestMobTypes loader */
	protected SummoningScrollInterpreter summonInterpreter;
	/** Custom skills listener used to test out the mobfilter function */
	protected CustomSkillsListener listener;
	
	public MobFilterTest ()
	{
		this.mobClasses = Mockito.mock(MonsterTypeInterpreter.class);
		this.summonInterpreter = Mockito.mock(SummoningScrollInterpreter.class);
		this.listener = new CustomSkillsListener (this.mobClasses, Mockito.mock(DamageDisplayListener.class), Mockito.mock(BossBarController.class), Mockito.mock(MobSkillRunner.class));
	}
	
	/** A series of tests to see if the test function calls from a Mocked Living Entity
	 *  works as intended */
	@Test
	public void testMockEntityCreation ()
	{
		SimpleUSCTestMob uscEquipment = new SimpleUSCTestMob ();
		SimpleNeutralTestMob neutralMob = new SimpleNeutralTestMob ();
		// Constructs a random list of nearby entities full of non-factioned zombies, who the USC mob is set to target
		int numNearbyEntities = 1000;
		List <Entity> nearbyEntities = new ArrayList <Entity> ();
		nearbyEntities.addAll(this.bulkCreateLivingEntities(numNearbyEntities, EntityType.ZOMBIE, neutralMob, new ArrayList <Entity> ()));
		LivingEntity entity = this.createMockLivingEntity(EntityType.ZOMBIE, uscEquipment, nearbyEntities);
		// Test Entity Type
		assert (entity.getType() == EntityType.ZOMBIE);
		// Test if the Monster Type Interpreter correctly returns the entity's assigned MobEquipment
		assert (this.mobClasses.getMobEquipmentFromEntity(entity).equals(uscEquipment));
		// Tests if the getNearbyEntities function returns the recently constructed nearbyEntities array
		assert (entity.getNearbyEntities(1,1,1).equals(nearbyEntities));
		assert (entity.getWorld().getNearbyEntities(entity.getLocation(), 1, 1, 1).equals(nearbyEntities));
		// Tests out the filterGoodAndBad entities function
		List <LivingEntity> allies = new ArrayList <LivingEntity> ();
		List <LivingEntity> enemies = new ArrayList <LivingEntity> ();
		this.listener.filterGoodAndBadEntities(entity, 1, allies, enemies);
		for (int i = 0; i < numNearbyEntities; ++i)
		{
			assert (nearbyEntities.get(i).equals(enemies.get(i)));
		}
	}
	
	/** Tests out to see if the mob filtering function is able to effectively filter out a pool of monsters who are apart of
	 *  a faction allied to the SkillCaster and another faction who is rivals to the SkillCaster */
	@Test
	public void testMobFilterSimple ()
	{
		int numFactionedMobs = 100;
		List <Entity> entities = new ArrayList <Entity> ();
		
		// Creates 100 allies and enemies surrounding the skill-caster
		SimpleUSCTestMob uscMob = new SimpleUSCTestMob ();
		SimpleUCLATestMob uclaTestMob = new SimpleUCLATestMob ();
		
		entities.addAll(this.bulkCreateLivingEntities(numFactionedMobs, EntityType.ZOMBIE, uscMob, new ArrayList <Entity> ()));
		entities.addAll(this.bulkCreateLivingEntities(numFactionedMobs, EntityType.SKELETON, uclaTestMob, new ArrayList <Entity> ()));
		
		assert (entities.size() == (numFactionedMobs + numFactionedMobs));
		LivingEntity caster = this.createMockLivingEntity(EntityType.ZOMBIE, uscMob, entities);
		
		List <LivingEntity> allies = new ArrayList <LivingEntity> ();
		List <LivingEntity> enemies = new ArrayList <LivingEntity> ();
		assert (caster.getWorld().getNearbyEntities(caster.getLocation(), 1, 1, 1).size() > 0);
		listener.filterGoodAndBadEntities(caster, 1, allies, enemies);
		
		assert (allies.size() == numFactionedMobs);
		assert (enemies.size() == numFactionedMobs);
		
		for (LivingEntity ally : allies)
		{
			assert (this.mobClasses.getMobEquipmentFromEntity(ally).toString().equals(uscMob.toString()));
		}
		
		for (LivingEntity enemy : enemies)
		{
			assert (this.mobClasses.getMobEquipmentFromEntity(enemy).toString().equals(uclaTestMob.toString()));
		}
	}
	
	/** Tests to see if the filtering out function effectively ignores ARMOR_STAND, ITEM_FRAME, and DROPPED_ITEM entities.
	 *  Both allies and enemies list should not include any living entities whose types fall into those categories. */
	@Test
	public void ignoreInanimateObjectsTest ()
	{
		int numUniqueEntities = 100;
		List <Entity> entities = new ArrayList <Entity> ();
		DamageDisplayEntity damageDisplayEntity = new DamageDisplayEntity ("PLACEHOLDER");
		entities.addAll (this.bulkCreateLivingEntities(numUniqueEntities, EntityType.ARMOR_STAND, damageDisplayEntity, entities));
		entities.addAll (this.bulkCreateLivingEntities(numUniqueEntities, EntityType.ITEM_FRAME, null, entities));
		entities.addAll (this.bulkCreateLivingEntities(numUniqueEntities, EntityType.DROPPED_ITEM, null, entities));
		
		LivingEntity caster = this.createMockLivingEntity(EntityType.ZOMBIE, new SimpleUSCTestMob (), entities);
		
		List <LivingEntity> allies = new ArrayList <LivingEntity> ();
		List <LivingEntity> enemies = new ArrayList <LivingEntity> ();
		listener.filterGoodAndBadEntities(caster, 1, allies, enemies);
		
		assert (allies.isEmpty() && enemies.isEmpty());
		
		entities.addAll(this.bulkCreateLivingEntities(numUniqueEntities, EntityType.SKELETON, new SimpleUSCTestMob (), entities));
		entities.addAll(this.bulkCreateLivingEntities(numUniqueEntities, EntityType.SKELETON, new SimpleUCLATestMob (), entities));
		allies.clear();
		enemies.clear();
		listener.filterGoodAndBadEntities(caster, numUniqueEntities, allies, enemies);
		assert (allies.size() == numUniqueEntities && enemies.size() == numUniqueEntities);
		
		EnumSet <EntityType> forbiddenTypes = EnumSet.of(EntityType.ARMOR_STAND, EntityType.ITEM_FRAME, EntityType.DROPPED_ITEM);
		for (int i = 0; i < numUniqueEntities; ++i)
		{
			assert (!forbiddenTypes.contains(allies.get(i).getType()) && !forbiddenTypes.contains(enemies.get(i).getType()));
		}
		
	}
	
	/** Creates mocked Living Entities in bulk
	 *  @param numEntities Total number of mocked living entities to be created
	 *  @param entityType Type of entity this mock entity is taking form of
	 *  @param equipment MobEquipment instance attached to a living entity
	 *  @param nearbyEntities A list of entities nearby to the living entity */
	public List <LivingEntity> bulkCreateLivingEntities (int numEntities, EntityType entityType, MobEquipment equipment, List <Entity> nearbyEntities)
	{
		List <LivingEntity> entities = new ArrayList <LivingEntity> ();
		for (int i = 0; i < numEntities; ++i)
		{
			entities.add(this.createMockLivingEntity(entityType, equipment, nearbyEntities));
		}
		return entities;
	}
	
	/** Creates a mocked instance of a living entity of a given entity type that is attached to a MobEquipment instance
	 * 	@param entityType Type of entity this mock entity is taking form of
	 *  @param equipment MobEquipment instance attached to a living entity
	 *  @param nearbyEntities A list of entities nearby to the living entity */
	public LivingEntity createMockLivingEntity (EntityType entityType, MobEquipment equipment, List <Entity> nearbyEntities)
	{
		LivingEntity mockEntity = Mockito.mock(LivingEntity.class);
		// Mock living entity getlocation
		Mockito.when(mockEntity.getLocation()).thenReturn(Mockito.mock(Location.class));
		// Mock Living Entity's getEntityType function
		Mockito.when(mockEntity.getType()).thenReturn(entityType);
		// Mock Living Entity's getPersistentDataContainer to a mock container object
		PersistentDataContainer mockContainer = Mockito.mock(PersistentDataContainer.class);
		Mockito.when(mockContainer.get(Mockito.any(), Mockito.any())).thenReturn(equipment != null ? equipment.toString() : null);
		Mockito.when(mockEntity.getPersistentDataContainer()).thenReturn(mockContainer);
		Mockito.when(this.mobClasses.getMobEquipmentFromEntity(mockEntity)).thenReturn(equipment);
		// Mock Living Entity's getNearbyEntities function
		Mockito.when(mockEntity.getNearbyEntities(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(nearbyEntities);
		// Mock Living Entity's getWorld's nearbyEntities function
		World mockWorld = Mockito.mock(World.class);
		Mockito.when(mockWorld.getNearbyEntities(Mockito.any(Location.class), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn (nearbyEntities);
		Mockito.when(mockEntity.getWorld()).thenReturn(mockWorld);
		return mockEntity;
	}
}
