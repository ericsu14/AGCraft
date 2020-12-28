package com.joojet.plugins.mobs.skills.utility;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.DamageDisplayListener;

public class AgressiveTeleportSkill extends TeleportSkill
{
	/** Creates a teleport skill where the entity warps to a random nearby enemy's location once the conditions in
	 *  TeleportSkill are met*/
	public AgressiveTeleportSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight);
	}
	
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener) 
	{	
		List <LivingEntity> possibleTargets = this.filterSubmergedEntities(enemies, caster);
		
		if (possibleTargets.isEmpty())
		{
			return;
		}
		
		LivingEntity target = possibleTargets.get(this.random.nextInt(possibleTargets.size()));
		this.teleportEntity(caster, target);
		// Gives the caster a glowing potion effect to let players know it has teleported
		if (target != null)
		{
			caster.addPotionEffect(new PotionEffect (PotionEffectType.GLOWING, 400, 0));
			target.sendMessage(ChatColor.RED + caster.getName() + " has teleported to your location.");
		}
	}
	
	/** Teleport to allied mobs is possible when:
	 * 	  - There is at least 1 nearby enemy
	 *    - The caster is submerged either in water or lava. */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return (!enemies.isEmpty() && this.isEngulfedInLiquids(caster));
	}

}
