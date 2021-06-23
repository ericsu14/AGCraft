package test.mobs.test_mobs;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class IgnoreNonFactionedTestMob extends MobEquipment
{
	public IgnoreNonFactionedTestMob ()
	{
		super (MonsterType.BARNEY_THE_DINOSAUR);
		
		this.addFactions(Faction.NETHER);
		this.addRivalFactions(Faction.USC, Faction.UCLA);
		this.addMobFlags(MobFlag.IGNORE_NON_FACTION_ENTITIES);
	}
}
