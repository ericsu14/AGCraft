package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.weightedentries.WeightedPotion;
import com.joojet.plugins.mobs.util.MathUtil;
import com.joojet.plugins.mobs.util.WeightedList;

public abstract class AbstractThrowPotionSkill extends AbstractAttackSkill 
{
	protected WeightedList <WeightedPotion, AbstractPotionEquipment> potionList;
	
	public AbstractThrowPotionSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight);
		this.potionList = new WeightedList <WeightedPotion, AbstractPotionEquipment> ();
		this.initializePotionsList();
	}
	
	/** Loads in the types of potions that can be randomly thrown upon using this skill */
	public abstract void initializePotionsList ();
	
	/** Runs filter operations on a list of enemies to get a list of possible targets to throw the potion to
	 *  @param caster Caster casting this skill
	 *  @param enemies An unfiltered list of enemies near the skill-caster */
	public abstract List <LivingEntity> getTargets (LivingEntity caster, List<LivingEntity> enemies);
	
	/** Plays animation effects on the caster using this skill */
	public abstract void playCasterAnimationEffects (LivingEntity caster, DamageDisplayListener damageDisplayListener);
	
	/** Adds a new potion into the possible list of potions that can be thrown while using this skill */
	public void addPotion (AbstractPotionEquipment potion, int weight)
	{
		potion.setType(Material.SPLASH_POTION);
		this.potionList.addEntry(new WeightedPotion (potion, weight));
	}
	
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		List <LivingEntity> targets = this.getTargets(caster, enemies);
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
				// Calculate the velocity vector between the caster and the farthest target
				Vector velocity = MathUtil.calculateArcBetweenPoints(caster.getLocation().toVector().clone(), targetLocation.toVector(), 
						(int) (caster.getHeight()), MathUtil.THROWN_PROJECTILE_GRAVITY);
				
				// Check if the velocity vector is finite. If not, skip spawning this anvil.
				try
				{
					velocity.checkFinite();
				}
				catch (IllegalArgumentException iae)
				{
					return;
				}
				
				ThrownPotion thrownPotion = caster.launchProjectile(ThrownPotion.class, velocity);
				thrownPotion.setItem(potionList.getRandomEntry());
			}
			
		}.runTaskLater(AGCraftPlugin.plugin, 10);
	}

}
