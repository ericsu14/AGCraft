package com.joojet.plugins.mobs.skills.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterLineOfSight;

/** Allows the skill-caster to teleport out of any enclosed area where establishing line of sight against all
 *  nearby enemies is impossible */
public class SituationalTeleportSkill extends TeleportSkill
{
	public SituationalTeleportSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight);
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		// Get nearby entities that isn't within the skill-caster's line of sight
		// and also super close to the skill-caster
		List <LivingEntity> possibleTargets = 
				enemies.stream().filter(ent -> !ent.hasLineOfSight(caster)).
				sorted (new ClosestProximity (caster)).collect(Collectors.toList());
		
		LivingEntity target = possibleTargets.get (0);
		this.teleportEntity(caster, target);
		if (target != null)
		{
			caster.addPotionEffect(new PotionEffect (PotionEffectType.GLOWING, 600, 0));
			target.sendMessage(caster.getName() + ChatColor.RED + " teleported to your location.");
		}
	}

	/** Only activate when there are no monsters within line of sight of the entity, but there are still enemies surrounding
	 *  the skill-caster */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return !enemies.isEmpty() && enemies.stream().filter(new FilterLineOfSight (caster)).
				collect(Collectors.toList()).isEmpty();
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		if (caster instanceof Monster)
		{
			return ((Monster) caster).getTarget() == null;
		}
		return false;
	}

}
