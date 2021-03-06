package com.joojet.plugins.mobs.skills.utility;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.stream.FilterSubmergedEntities;

public class TeleportSkill extends AbstractSkill {
	
	/** A set of materials that defines water or lava source blocks */
	EnumSet <Material> liquidSourceBlocks;
	
	/** Allows the caster to teleport to a random nearby ally, if one exists
	 *  @param range Max scan radius for this teleportation skill
	 *  @param cooldown Cooldown of the teleport skill in seconds
	 *  @param maxUses Max. amount of times this skill can be used
	 *  @param weight Weight of the skill */
	public TeleportSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(SkillPropetry.UTILITY, range, cooldown, maxUses, weight);
		this.liquidSourceBlocks = EnumSet.of(Material.LAVA, Material.WATER);
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{		
		List <LivingEntity> possibleTargets = allies.stream().filter(new FilterSubmergedEntities (caster)).
				sorted(new ClosestProximity (caster.getLocation().clone())).
				collect(Collectors.toList());
		
		if (possibleTargets.isEmpty())
		{
			return;
		}
		
		
		LivingEntity target = possibleTargets.get(0);
		this.teleportEntity(caster, target);
	}
	
	/** Returns true if the entity is engulfed in a liquid source block */
	protected boolean isEngulfedInLiquids (LivingEntity entity)
	{
		return this.liquidSourceBlocks.contains(entity.getLocation().getBlock().getType()) ||
				this.liquidSourceBlocks.contains(entity.getEyeLocation().getBlock().getType());
	}

	/** Teleport to allied mobs is possible when:
	 *    - There is at least one nearby ally and no nearby enemies.
	 *    - The caster is submerged either in water or lava. */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return (!allies.stream().filter(new FilterSubmergedEntities (caster)).
				collect(Collectors.toList()).isEmpty());
	}
	
	/** The caster can only use this skill when surrounded by liquids */
	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.isEngulfedInLiquids(caster);
	}
	
	/** Teleports an entity to a target entity's location
	 *  @param caster - Entity to be teleported
	 *  @param target - Target */
	protected void teleportEntity (LivingEntity caster, LivingEntity target)
	{
		if (target != null)
		{
			caster.getWorld().spawnParticle(Particle.SPELL_WITCH, caster.getLocation(), 20, 1.0, 1.0, 1.0);
			caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
			Location teleportLocation = target.getLocation();
			caster.teleport(teleportLocation);
			caster.getWorld().spawnParticle(Particle.SPELL_WITCH, teleportLocation, 20, 1.0, 1.0, 1.0);
			caster.getWorld().playSound(teleportLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
		}
	}

}
