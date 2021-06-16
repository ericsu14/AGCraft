package mobs.test_mobs;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** A simple USC mob who is part of the USC faction and rivals with the UCLA faction */
public class SimpleUSCTestMob extends MobEquipment
{

	public SimpleUSCTestMob() 
	{
		super(MonsterType.HG_USC_WARRIOR);
		this.addFactions(Faction.USC);
		this.addRivalFactions(Faction.UCLA);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.IRON_GOLEM);
		
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON);
	}

}
