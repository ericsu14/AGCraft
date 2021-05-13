package com.joojet.plugins.mobs.enums;

import java.util.List;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.*;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interfaces.CustomAttribute;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.ArrowDamageModifierSkill;
import com.joojet.plugins.mobs.skills.passive.CriticalShotSkill;
import com.joojet.plugins.mobs.skills.passive.NoOpSkill;
import com.joojet.plugins.mobs.skills.passive.PiercingBlowSkill;

public enum MonsterStat implements CustomAttribute, CustomSkillUser
{
	/** Entity's base health */
	HEALTH (Attribute.GENERIC_MAX_HEALTH),
	/** The amount of EXP dropped from this entity */
	EXPERIENCE,
	/** The max. radius used for determining which nearby player to hunt when the flag HUNT_ON_SPAWN is enabled */
	HUNT_ON_SPAWN_RADIUS,
	/** Modifies the entity's base attack damage */
	BASE_ATTACK_DAMAGE (Attribute.GENERIC_ATTACK_DAMAGE),
	/** The minimum y value the original entity must be in order for this entity to spawn */
	Y_LIMIT,
	/** Stores the enum ordinal relating to the monster's horse color */
	HORSE_COLOR
	{
		@Override
		public void applyCustomAttributes(LivingEntity entity, MobEquipment entityEquipment,
				BossBarController bossBarController) 
		{
			if (entity instanceof Horse)
			{
				Horse horse = (Horse) entity;
				horse.setColor(Color.values()
						[entityEquipment.getStat(MonsterStat.HORSE_COLOR).intValue()]);
			}
		}
	},
	/** Stores the enum ordinal relating to the monster's horse style */
	HORSE_STYLE
	{
		@Override
		public void applyCustomAttributes(LivingEntity entity, MobEquipment entityEquipment,
				BossBarController bossBarController) 
		{
			if (entity instanceof Horse)
			{
				Horse horse = (Horse) entity;
				horse.setStyle(Style.values()
						[entityEquipment.getStat(MonsterStat.HORSE_STYLE).intValue()]);
			}
		}
	},
	/** Modifies the jump strength if this custom mob is a horse */
	HORSE_JUMP_STRENGTH (Attribute.HORSE_JUMP_STRENGTH),
	/** Modifies the base speed of the mob */
	BASE_SPEED (Attribute.GENERIC_MOVEMENT_SPEED),
	/** Modifies the base damage of shot arrows */
	BASE_ARROW_DAMAGE
	{
		@Override
		public void loadCustomSkills(List<AbstractSkill> skills) 
		{
			skills.add(new ArrowDamageModifierSkill ());
		}
	},
	/** Random chance (from 0.00-1.00) of entity-shot arrows from becoming a critical hit arrow */
	ARROW_CRITICAL_CHANCE
	{
		@Override
		public void loadCustomSkills(List<AbstractSkill> skills) 
		{
			skills.add(new CriticalShotSkill ());
		}
	},
	/** Random chance (from 0.00-1.00) of entity-shot critical arrows from having a piercing effect, being able to rip
	 *  through shields. */
	ARROW_PIERCING_CHANCE
	{
		@Override
		public void loadCustomSkills(List<AbstractSkill> skills) 
		{
			skills.add(new PiercingBlowSkill ());
		}
	},
	/** Modifies the base armor value of custom mobs */
	BASE_ARMOR (Attribute.GENERIC_ARMOR),
	/** Modifies the base armor toughness value of custom mobs */
	BASE_ARMOR_TOUGHNESS (Attribute.GENERIC_ARMOR_TOUGHNESS),
	/** Modifies the knockback resistance stat of custom mobs */
	KNOCKBACK_RESISTANCE (Attribute.GENERIC_KNOCKBACK_RESISTANCE),
	/** Modified the knockback strength for the custom mob's melee attack */
	BASE_KNOCKBACK_STRENGTH (Attribute.GENERIC_ATTACK_KNOCKBACK),
	/** Sets the custom monster's classifier */
	MONSTER_CLASSIFIER,
	/** Max amount of times this custom monster can spawn in the world */
	SPAWN_LIMIT
	{
		@Override
		public void loadCustomSkills(List<AbstractSkill> skills) 
		{
			skills.add(new NoOpSkill ());
		}
	},
	/** Sets a delay (in seconds) before the monster's spawn limit counter is decremented, effectively giving a temporary cooldown period before the custom
	 *  monster can spawn again. */
	SPAWN_LIMIT_COOLDOWN;
	
	/** The Minecraft entity attribute this monsterstat is tied to */
	private Attribute attribute;
	
	private MonsterStat ()
	{
		this.attribute =  null;
	}
	
	private MonsterStat (Attribute attribute)
	{
		this.attribute = attribute;
	}
	
	/** Returns true if this monster stat is tied to a Minecraft entity attribute */
	public boolean containsAttribute ()
	{
		return this.attribute != null;
	}
	
	/** Returns the Minecraft entity attribute tied to this monster stat */
	public Attribute getAttribute ()
	{
		return this.attribute;
	}
	
	/** Does nothing when the mobflag does not have a custom definition specific for that attribute */
	@Override
	public void applyCustomAttributes(LivingEntity entity, MobEquipment entityEquipment,
			BossBarController bossBarController) 
	{

	}
	
	/** Does nothing when the mob stat does not give a custom skill */
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		
	}
}
