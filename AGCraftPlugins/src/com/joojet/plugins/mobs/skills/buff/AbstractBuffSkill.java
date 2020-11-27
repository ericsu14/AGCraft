package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.spawning.FairSpawnController;

public abstract class AbstractBuffSkill extends AbstractSkill {
	
	/** Potion effect to be used */
	protected PotionEffectType potionType;
	/** Duration of the potion effect (in seconds)*/
	protected int potionDuration;
	/** Strength of the potion effect */
	protected int potionStrength;
	/** Used to calculate fair spawn weights */
	protected FairSpawnController spawnWeight;
	
	/** Creates an abstract buff skill, which applies a potion effect to the caster itself and
	 *  its surrounding allies
	 *  @param potionType - Type of potion to be used
	 *  @param potionDuration - Duration of the potion (in seconds)
	 *  @param potionStrength - Potion strength
	 *  @param range - Max range of the potion
	 *  @param weight - Weight of this buff skill */
	public AbstractBuffSkill(PotionEffectType potionType, int potionDuration, int potionStrength, int range, int weight) 
	{
		super(SkillPropetry.BUFF, range, potionDuration, Integer.MAX_VALUE, weight);
		this.potionDuration = potionDuration * 20;
		this.potionStrength = potionStrength;
		this.potionType = potionType;
		this.spawnWeight = new FairSpawnController (this.getRange());
	}
	
	/** Gives the caster and any of its surrounding allies a Strength I buff. */
	@Override
	protected void handleSkill(LivingEntity caster, ArrayList<LivingEntity> allies, ArrayList<LivingEntity> enemies) 
	{
		this.applyPotionEffect(caster, this.potionType, this.potionDuration, this.potionStrength);
		this.playBuffAnimation(caster);
		
		/** Apply buffs on allies a half second later */
		new BukkitRunnable () {
			@Override
			public void run ()
			{
				for (LivingEntity ally : allies)
				{
					if (!hasPotionEffect (ally) && ally.getType() != EntityType.CREEPER)
					{
						applyPotionEffect(ally, potionType, potionDuration, potionStrength);
						playBuffAnimation(ally);
					}
				}
			}
		}.runTaskLater(AGCraftPlugin.plugin, 30);
	}
	
	/** Animation used for when the entity is affected by a buff */
	protected abstract void playBuffAnimation (LivingEntity entity);

	/** Applies a potion effect of choice to an entity if the entity doesn't already have it */
	protected void applyPotionEffect (LivingEntity entity, PotionEffectType potion, int duration, int strength)
	{
		if (!entity.hasPotionEffect(potion))
		{
			entity.addPotionEffect(new PotionEffect (potion, duration, strength));
		}
	}
	
	/** Returns true if the entity has the desired potion effect that matches the set strength and effect. */
	public boolean hasPotionEffect (LivingEntity entity)
	{
		return (entity.hasPotionEffect(this.potionType) && entity.getPotionEffect(this.potionType).getAmplifier() <= this.potionStrength);
	}
}