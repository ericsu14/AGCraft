package com.joojet.plugins.mobs.skills.utility;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

public class TeleportSkill extends AbstractSkill {
	
	/** A set of materials that defines water or lava source blocks */
	EnumSet <Material> liquidSourceBlocks;
	
	/** Allows the caster to teleport to a random nearby ally, if one exists
	 *  @param range Max scan radius for this teleportation skill
	 *  @param cooldown Cooldown of the teleport skill in seconds
	 *  @param maxUses Max. amount of times this skill can be used
	 *  @param weight Weight of the skill */
	public TeleportSkill(int range, int cooldown, int maxUses, int weight) {
		super(SkillPropetry.UTILITY, range, cooldown, maxUses, weight);
		this.liquidSourceBlocks = EnumSet.of(Material.LAVA, Material.WATER);
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener) 
	{		
		List <LivingEntity> possibleTargets = this.filterSubmergedEntities(allies, caster);
		
		if (possibleTargets.isEmpty())
		{
			return;
		}
		
		LivingEntity target = possibleTargets.get(this.random.nextInt(possibleTargets.size()));
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
		return (!allies.isEmpty() && this.isEngulfedInLiquids(caster));
	}
	
	/** Filters a list of entities by removing entities who are submerged under a liquid source block.
	 *  @param entities A list of entities to be filtered
	 *  @param caster The living entity using the skill, whose height is used to check if the warp location has enough room to accommodate
	 *                the caster's height. */
	protected List <LivingEntity> filterSubmergedEntities (List <LivingEntity> entities, LivingEntity caster)
	{
		Object [] filtered = entities.stream().filter(ent -> (!this.isEngulfedInLiquids(ent) && checkSurroundingArea (caster, ent))).toArray();
		return Arrays.asList(Arrays.copyOf(filtered, filtered.length, LivingEntity[].class));
	}
	
	/** Uses the caster's bounding box to check if there is enough room (denoted by air blocks) around a location
	 *  for the caster to safely teleport to
	 *  @param caster Skillcaster using the skill
	 *  @param teleportLocation Location being checked */
	protected boolean checkSurroundingArea (LivingEntity caster, LivingEntity target)
	{
		World world = caster.getWorld();
		Block block = null;
		
		BoundingBox scanArea = BoundingBox.of(target.getLocation().add(0.0, caster.getHeight() / 2.0, 0.0), 
				caster.getWidth() / 2.0, caster.getHeight() / 2.0, caster.getWidth() / 2.0);
		
		for (int i = (int) scanArea.getMinX(); i <= (int) scanArea.getMaxX(); ++i)
		{
			for (int j = (int) scanArea.getMinY(); j <= (int) scanArea.getMaxY(); ++j)
			{
				for (int k = (int) scanArea.getMinZ(); k <= (int) scanArea.getMaxZ(); ++k)
				{
					block = world.getBlockAt(i, j, k);
					if (block == null || block.getType() != Material.AIR)
					{
						return false;
					}
				}
			}
		}
		return true;
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
