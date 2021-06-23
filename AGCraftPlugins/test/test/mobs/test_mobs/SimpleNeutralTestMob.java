package test.mobs.test_mobs;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** Represents a mob who is not apart of any faction */
public class SimpleNeutralTestMob extends MobEquipment
{

	public SimpleNeutralTestMob() 
	{
		super(MonsterType.BADASS_ZOMBIE);
	}

}
