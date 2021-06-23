package test.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.junit.Test;
import org.mockito.Mockito;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.CustomSkillsListener;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;

import test.mobs.test_mobs.SimpleUCLATestMob;
import test.mobs.test_mobs.SimpleUSCTestMob;
import test.mobs.test_mobs.TestMobTypes;

public class MobFilterTest 
{
	/** Mob interpreter instance used for storing and looking up MobEquipment instances used for testing */
	protected MonsterTypeInterpreter mobClasses;
	/** Summoning scroll interpreter used as a dependency for the TestMobTypes loader */
	protected SummoningScrollInterpreter summonInterpreter;
	protected TestMobTypes testMonsters;
	
	public MobFilterTest ()
	{
		this.mobClasses = new MonsterTypeInterpreter ();
		this.summonInterpreter = Mockito.mock(SummoningScrollInterpreter.class);
		this.testMonsters = new TestMobTypes (this.mobClasses, this.summonInterpreter);
	}
	
	@Test
	public void testMockEntityCreation ()
	{
		
		SimpleUSCTestMob uscEquipment = new SimpleUSCTestMob ();
		LivingEntity entity = this.createMockLivingEntity(EntityType.ZOMBIE, uscEquipment, new ArrayList <Entity> ());
		
		assert (entity.getType() == EntityType.ZOMBIE);
		assert (this.mobClasses.getMobEquipmentFromEntity(entity).toString().equals(uscEquipment.toString()));
	}
	
	@Test
	public void testMobFilterSimple ()
	{
		int numFactionedMobs = 100;
		List <Entity> entities = new ArrayList <Entity> ();
		
		List <MobEquipment> expectedAllies = new ArrayList <MobEquipment> ();
		List <MobEquipment> expectedEnemies = new ArrayList <MobEquipment> ();
		
		for (int i = 0; i < numFactionedMobs; ++i)
		{
			SimpleUSCTestMob uscMob = new SimpleUSCTestMob ();
			expectedAllies.add(uscMob);
			entities.add(this.createMockLivingEntity(EntityType.ZOMBIE, uscMob, new ArrayList <Entity> ()));
			SimpleUCLATestMob uclaTestMob = new SimpleUCLATestMob ();
			expectedEnemies.add(uclaTestMob);
			entities.add(this.createMockLivingEntity(EntityType.ZOMBIE, uclaTestMob, new ArrayList <Entity> ()));
		}
		
		CustomSkillsListener listener = new CustomSkillsListener (null, Mockito.mock(DamageDisplayListener.class), Mockito.mock(BossBarController.class), Mockito.mock(MobSkillRunner.class));
		
	}
	
	/** Creates a mocked instance of a living entity of a given entity type that is attached to a MobEquipment instance */
	public LivingEntity createMockLivingEntity (EntityType entityType, MobEquipment equipment, List <Entity> nearbyEntities)
	{
		LivingEntity mockEntity = Mockito.mock(LivingEntity.class);
		// Mock Living Entity's getEntityType function
		Mockito.when(mockEntity.getType()).thenReturn(entityType);
		// Mock Living Entity's getPersistentDataContainer to a mock container object
		PersistentDataContainer mockContainer = Mockito.mock(PersistentDataContainer.class);
		Mockito.when(mockContainer.get(Mockito.any(), Mockito.any())).thenReturn(equipment.toString());
		Mockito.when(mockEntity.getPersistentDataContainer()).thenReturn(mockContainer);
		// Mock Living Entity's getNearbyEntities function
		Mockito.when(mockEntity.getNearbyEntities(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(nearbyEntities);
		return mockEntity;
	}
}
