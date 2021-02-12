package com.joojet.plugins.mobs.skills.summon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.SkillSummonedMetadata;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.skills.runnable.SummonEntitiesTask;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.mobs.util.LocationTools;
import com.joojet.plugins.warp.scantools.ScanEntities;

public abstract class AbstractSummonSkill extends AbstractSkill
{
	/** The total amount of monsters that can be summoned */
	protected int maxSummons;
	/** A list of custom monsters this monster can randomly summon */
	protected List <WeightedMobSummon> summons;
	/** Internally keeps track of the min. weight used for the next inserted summon in the summon list */
	private int minWeight;
	/** Internally keeps track of the max. weight of the last summono inserted in the summon list */
	private int maxWeight;
	
	/** Creates a new instance of a summoning skill, allowing the monster to summon custom monsters at will. */
	public AbstractSummonSkill(int range, int cooldown, int maxUses, int weight, int maxSummons) 
	{
		super(SkillPropetry.SUMMON, range, cooldown, maxUses, weight);
		this.maxSummons = maxSummons;
		this.minWeight = 0;
		this.maxWeight = 0;
		this.summons = new ArrayList <WeightedMobSummon> ();
		this.initializeSummons();
	}
	
	/** A function used to populate the list of possible custom monsters this mob can summon */
	public abstract void initializeSummons ();
	
	@Override
	protected void handleSkill (LivingEntity caster, List <LivingEntity> allies, List <LivingEntity> enemies, DamageDisplayListener damageDisplayListener,
			MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		List <Location> possibleLocations = this.getSpawnPoints(caster);
		if (!possibleLocations.isEmpty())
		{
			this.playSkillCasterAnimation(caster, damageDisplayListener);
			for (Player player : ScanEntities.ScanNearbyPlayers(caster, this.range))
			{
				player.sendMessage(this.getSentMessage(caster));
			}
			new SummonEntitiesTask (possibleLocations, this, monsterTypeInterpreter, bossBarController, damageDisplayListener).runTaskTimer(AGCraftPlugin.plugin, 20, 4);
		}
	}
	
	/** Plays a summon animation on the skill caster */
	public abstract void playSkillCasterAnimation (LivingEntity caster, DamageDisplayListener damageDisplayListener);
	
	/** Plays a summon animation on a summoned entity */
	public abstract void playSummonAnimation (LivingEntity entity, DamageDisplayListener damageDisplayListener);
	
	/** The message that is sent to all nearby players when the caster uses this skill */
	public abstract String getSentMessage (LivingEntity caster);
	
	/** Registers a new summon into the list of custom monsters that can be summoned by this skill
	 *  @param monsterType Type of custom monster that can be summoned
	 *  @param weight Custom monster's spawn weight, which alters the frequency this monster is summoned with this skill. */
	public void addSummon (MonsterType monsterType, EntityType entityType, int weight)
	{
		this.maxWeight = this.minWeight + weight;
		this.summons.add(new WeightedMobSummon (monsterType, entityType, this.minWeight, maxWeight));
		this.minWeight = maxWeight + 1;
	}
	
	/** Returns the list of monsters that can be randomly summoned by this skill */
	public List <WeightedMobSummon> getSummons ()
	{
		return this.summons;
	}
	
	/** Returns the max spawn weight for all loaded summons in the summon list */
	public int getMaxSpawnWeight ()
	{
		return this.maxWeight;
	}
	
	/** Overrides the existing canUseSkill function to also account for checking if the caster is a skill-summoned entity or not */
	@Override
	public boolean canUseSkill (LivingEntity caster)
	{
		return super.canUseSkill(caster) && new SkillSummonedMetadata().getStringMetadata(caster) == null;
	}
	
	/** Attempts to search around the caster's surrounding area to find any potential locations the entity can spawn in. */
	protected List <Location> getSpawnPoints (LivingEntity caster)
	{
		List <Location> locations = new ArrayList <Location> ();
		
		for (int i = 0; i < this.maxSummons; ++i)
		{
			Location loc = LocationTools.addRandomXZOffsetOnLocation(caster.getLocation(), this.range / 2);
			if (EquipmentTools.checkSpawnSpace(loc, 4.0))
			{
				locations.add(loc);
			}
		}
		return locations;
	}
}
