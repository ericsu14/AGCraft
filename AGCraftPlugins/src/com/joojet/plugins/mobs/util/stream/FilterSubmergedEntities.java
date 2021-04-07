package com.joojet.plugins.mobs.util.stream;

import java.util.EnumSet;
import java.util.function.Predicate;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.util.LocationTools;

public class FilterSubmergedEntities implements Predicate <LivingEntity>
{
	/** The skill-caster */
	protected LivingEntity caster;
	/** A set of materials that defines water or lava source blocks */
	protected EnumSet <Material> liquidSourceBlocks;
	
	public FilterSubmergedEntities (LivingEntity caster)
	{
		this.liquidSourceBlocks = EnumSet.of(Material.LAVA, Material.WATER);
		this.caster = caster;
	}
	
	@Override
	public boolean test(LivingEntity ent) 
	{
		return !this.isEngulfedInLiquids(ent) && LocationTools.checkSurroundingArea (this.caster, ent);
	}
	
	/** Returns true if the entity is engulfed in a liquid source block */
	protected boolean isEngulfedInLiquids (LivingEntity entity)
	{
		return this.liquidSourceBlocks.contains(entity.getLocation().getBlock().getType()) ||
				this.liquidSourceBlocks.contains(entity.getEyeLocation().getBlock().getType());
	}

}
