package mobs.test_mobs;

import org.bukkit.entity.EntityType;

public class SimpleZombieDestroyer extends SimpleNeutralTestMob
{
	public SimpleZombieDestroyer ()
	{
		super();
		this.addTargetsToHitList(EntityType.ZOMBIE);
	}
}
