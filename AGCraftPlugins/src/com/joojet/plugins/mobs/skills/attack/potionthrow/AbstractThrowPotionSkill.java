package com.joojet.plugins.mobs.skills.attack.potionthrow;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.ThrowablePotionType;
import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.attack.AbstractAttackSkill;
import com.joojet.plugins.mobs.skills.enums.TargetSelector;
import com.joojet.plugins.mobs.skills.weightedentries.WeightedPotion;
import com.joojet.plugins.mobs.util.MathUtil;
import com.joojet.plugins.mobs.util.WeightedList;

public abstract class AbstractThrowPotionSkill extends AbstractAttackSkill 
{
	/** Determines the type of entities this skill-caster should throw the potion to */
	protected TargetSelector targetSelection;
	
	/** Contains a weighted list of PotionEquipment the skill caster can throw */
	protected WeightedList <WeightedPotion, AbstractPotionEquipment> potionList;
	
	/** Creates a new abstract potion throw skill, allowing entities to throw potions at a nearby target (or self)
	 *  @param range Max. radius in which the skillcaster is able to use this skill
	 *  @param cooldown Cooldown time (in seconds) before the skill can be used again
	 *  @param maxUses Total amount of times the skill can be used. Setting this parameter to Integer.MAX_VALUE allows this skill
	 *                 to be used infinitely
	 *  @param weight Sets the skill's priority for it to be randomly selected each second.
	 *  @param targetSelection Determines the type of entities the potion should be thrown to. */
	public AbstractThrowPotionSkill(int range, int cooldown, int maxUses, int weight, TargetSelector targetSelection) 
	{
		super(range, cooldown, maxUses, weight);
		this.potionList = new WeightedList <WeightedPotion, AbstractPotionEquipment> ();
		this.initializePotionsList();
		this.targetSelection = targetSelection;
	}
	
	/** Loads in the types of potions that can be randomly thrown upon using this skill */
	public abstract void initializePotionsList ();
	
	/** Runs filter operations on a list of enemies to get a list of possible targets to throw the potion to
	 *  @param caster Caster casting this skill
	 *  @param enemies An unfiltered list of enemies near the skill-caster */
	public abstract List <LivingEntity> getTargets (LivingEntity caster, List<LivingEntity> entities);
	
	/** Plays animation effects on the caster using this skill */
	public abstract void playCasterAnimationEffects (LivingEntity caster, DamageDisplayListener damageDisplayListener);
	
	/** Adds a new potion into the possible list of potions that can be thrown while using this skill */
	public void addPotion (AbstractPotionEquipment potion, int weight, ThrowablePotionType potionType)
	{
		// Don't change the potion's type if it is already the same as potionType
		if (potion.getType() != potionType.getMaterial())
		{
			potion.setType(potionType.getMaterial());
		}
		this.potionList.addEntry(new WeightedPotion (potion, weight));
	}
	
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		List <LivingEntity> targets;
		
		// Selects targets based on the passed target selector to this skill
		switch (this.targetSelection)
		{
			case ALL:
				targets = this.getTargets(caster, enemies);
				targets.addAll(this.getTargets(caster, allies));
				break;
			case ALLIES:
				targets = this.getTargets(caster, allies);
				break;
			case ENEMIES:
				targets = this.getTargets(caster, enemies);
				break;
			default:
				List <LivingEntity> casterList = new ArrayList <LivingEntity> ();
				casterList.add(caster);
				targets = this.getTargets(caster, casterList);
				break;
		}
		
		if (this.potionList.isEmpty())
		{
			return;
		}
		
		// Filters out entities who has at least one active potion effect of the randomly thrown potion
		AbstractPotionEquipment potion = potionList.getRandomEntry();
		PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
		targets = targets.stream().filter((entity) -> 
		{
			for (PotionEffect potionEffect : potionMeta.getCustomEffects())
			{
				if (entity.hasPotionEffect(potionEffect.getType()))
				{
					return false;
				}
			}
			return true;
		}).toList();
		
		if (targets.isEmpty())
		{
			return;
		}
		
		LivingEntity target = targets.get(0);
		Location targetLocation = target.getLocation().clone();
		this.playCasterAnimationEffects(caster, damageDisplayListener);
		
		new BukkitRunnable () 
		{
			@Override
			public void run() 
			{
				Location potionSpawnLocation = caster.getEyeLocation().add(caster.getEyeLocation().getDirection()).clone();
				// Calculate the velocity vector between the caster and the farthest target
				Vector velocity = targetSelection != TargetSelector.SELF ? MathUtil.calculateArcBetweenPoints(potionSpawnLocation.toVector(), targetLocation.toVector(), 
						(int) (caster.getHeight()), MathUtil.THROWN_PROJECTILE_GRAVITY) : null;
				
				// Check if the velocity vector is finite. If not, skip spawning this potion.
				if (velocity != null)
				{
					try
					{
						velocity.checkFinite();
					}
					catch (IllegalArgumentException iae)
					{
						return;
					}
				}
				
				caster.getWorld().spawn(potionSpawnLocation, ThrownPotion.class, entity -> {
					
					if (velocity != null)
					{
						entity.setVelocity(velocity);
					}
					entity.setItem(potion);
					entity.setShooter(caster);
				});
			}
			
		}.runTaskLater(AGCraftPlugin.plugin, 10);
	}

}
