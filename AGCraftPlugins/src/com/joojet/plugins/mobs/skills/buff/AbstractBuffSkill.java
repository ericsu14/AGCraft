package com.joojet.plugins.mobs.skills.buff;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
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
	public AbstractBuffSkill(PotionEffectType potionType, int potionDuration, int potionStrength, int range, int cooldown, int weight) 
	{
		super(SkillPropetry.BUFF, range, cooldown, Integer.MAX_VALUE, weight);
		this.potionDuration = potionDuration * 20;
		this.potionStrength = potionStrength;
		this.potionType = potionType;
		this.spawnWeight = new FairSpawnController (this.getRange() / 2);
	}
	
	/** Gives the caster and any of its surrounding allies a Strength I buff. */
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies, DamageDisplayListener damageDisplayListener) 
	{
		this.applyPotionEffect(caster, this.potionType, this.potionDuration, this.potionStrength);
		this.playBuffAnimation(caster);
		if (!this.getBuffText().isEmpty())
		{
			damageDisplayListener.displayStringAboveEntity(caster, this.getBuffText());
		}
		
		/** Apply buffs on allies a half second later */
		new BukkitRunnable () {
			@Override
			public void run ()
			{
				for (LivingEntity ally : allies)
				{
					if (ally != null && !ally.isDead() 
							&& !hasPotionEffect (ally) && ally.getType() != EntityType.CREEPER)
					{
						applyPotionEffect(ally, potionType, potionDuration, potionStrength);
						playBuffAnimation(ally);
						if (!getBuffText().isEmpty())
						{
							damageDisplayListener.displayStringAboveEntity(ally, getBuffText());
						}
					}
				}
			}
		}.runTaskLater(AGCraftPlugin.plugin, 30);
	}
	
	/** Animation used for when the entity is affected by a buff */
	protected abstract void playBuffAnimation (LivingEntity entity);
	
	/** Returns a String that is displayed to those affected by the buff */
	protected abstract String getBuffText ();

	/** Applies a potion effect of choice to an entity if the entity doesn't already have it */
	protected void applyPotionEffect (LivingEntity entity, PotionEffectType potion, int duration, int strength)
	{
		if (!entity.hasPotionEffect(potion))
		{
			entity.addPotionEffect(new PotionEffect (potion, duration, strength, false, true));
		}
	}
	
	/** Returns true if the entity has the desired potion effect that matches the set strength and effect. */
	public boolean hasPotionEffect (LivingEntity entity)
	{
		return (entity.hasPotionEffect(this.potionType) && 
				(entity.getPotionEffect(this.potionType).getAmplifier() <= this.potionStrength && 
				entity.getPotionEffect(this.potionType).getDuration() <= this.potionDuration));
	}
	
	/** Returns true if there is at least one player around the caster
	 * 	@param entities - A list of LivingEntities */
	public boolean checkForSurroundingPlayers (List <LivingEntity> entities)
	{
		for (LivingEntity entity : entities)
		{
			if (entity.getType() == EntityType.PLAYER)
			{
				return true;
			}
		}
		return false;
	}
}
