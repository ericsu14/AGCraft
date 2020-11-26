package com.joojet.plugins.mobs.skills.buff;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.spawning.FairSpawnController;

public abstract class AbstractBuffSkill extends AbstractSkill {
	
	/** Used to calculate fair spawn weights */
	protected FairSpawnController spawnWeight;
	
	public AbstractBuffSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(SkillPropetry.BUFF, range, cooldown, maxUses, weight);
		this.spawnWeight = new FairSpawnController (this.getRange());
	}

	 
	/** Applies a potion effect of choice to an entity if the entity doesn't already have it */
	protected void applyPotionEffect (LivingEntity entity, PotionEffectType potion, int duration, int strength)
	{
		if (!entity.hasPotionEffect(potion))
		{
			entity.addPotionEffect(new PotionEffect (potion, duration, strength));
		}
	}
}
