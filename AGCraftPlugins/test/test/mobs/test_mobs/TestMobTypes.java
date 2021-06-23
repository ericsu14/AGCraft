package test.mobs.test_mobs;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;
import com.joojet.plugins.mobs.monsters.WeightedMob;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class TestMobTypes extends MonsterTypes
{

	public TestMobTypes(MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.ZOMBIE, EntityType.SKELETON);
		this.addEquipment(new IgnoreNonFactionedTestMob (), 1);
		this.addEquipment(new SimpleNeutralTestMob(), 1);
		this.addEquipment(new SimpleUCLATestMob (), 1);
		this.addEquipment(new SimpleUSCTestMob (), 1);
		this.addEquipment(new SimpleZombieDestroyer(), 1);
	}
	
	/** Prevents summoning scroll interpreter from being used */
	@Override
	public void addEquipment (MobEquipment equipment, int weight)
	{
		equipmentList.add(new WeightedMob (equipment, weight));
		this.monsterTypeInterpreter.insertWord(equipment.toString(), equipment);
		++this.size;
	}

}
