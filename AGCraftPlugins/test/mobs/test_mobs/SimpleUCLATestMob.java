package mobs.test_mobs;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** A simple USC mob who is part of the USC faction and rivals with the UCLA faction */
public class SimpleUCLATestMob extends MobEquipment 
{
	public SimpleUCLATestMob() 
	{
		super(MonsterType.HG_UCLA_JOCK);
		this.addFactions(Faction.UCLA);
		this.addRivalFactions(Faction.USC);
		
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON);
	}

}
