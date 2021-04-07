package com.joojet.plugins.mobs.skills.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterNonPlayerEntities;
import com.joojet.plugins.mobs.util.stream.FilterSubmergedEntities;

public class AggressiveTeleportSkill extends TeleportSkill
{
	/** Creates a teleport skill where the entity warps to a random nearby enemy's location once the conditions in
	 *  TeleportSkill are met*/
	public AggressiveTeleportSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight);
	}
	
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{		
		/* List <LivingEntity> possibleTargets = this.convertStreamToList(
				this.sortByClosestProximity(
						this.filterNonPlayerEntities(
								this.filterSubmergedEntities(enemies.stream(), caster)
								), 
						caster)); */
		
		List <LivingEntity> possibleTargets = enemies.stream().
				filter(new FilterNonPlayerEntities()).
				filter(new FilterSubmergedEntities(caster)).
				sorted (new ClosestProximity (caster.getLocation().clone())).collect(Collectors.toList());
		
		if (possibleTargets.isEmpty())
		{
			return;
		}
		
		LivingEntity target = possibleTargets.get(0);
		this.teleportEntity(caster, target);
		// Gives the caster a glowing potion effect to let players know it has teleported
		if (target != null)
		{
			caster.addPotionEffect(new PotionEffect (PotionEffectType.GLOWING, 600, 0));
			target.sendMessage(caster.getName() + ChatColor.RED + " teleported to your location.");
		}
	}
	
	/** Teleport to allied mobs is possible when:
	 * 	  - There is at least 1 nearby enemy
	 *    - The caster is submerged either in water or lava. */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return (!enemies.stream().filter(new FilterNonPlayerEntities()).
				filter(new FilterSubmergedEntities (caster)).
				collect(Collectors.toList()).isEmpty());
	}

}
