package com.joojet.plugins.mobs.enums;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interfaces.CustomAttribute;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.interfaces.CustomSpawnMessage;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.DisableDrowningSkill;
import com.joojet.plugins.mobs.skills.passive.DisableMagicHealSkill;
import com.joojet.plugins.mobs.skills.passive.DisableMeltingSkill;
import com.joojet.plugins.mobs.skills.passive.DisableSuffocationSkill;
import com.joojet.plugins.warp.scantools.ScanEntities;

public enum MobFlag implements CustomAttribute, CustomSkillUser
{
	/** The entity will spawn with a permanent burning effect */
	ON_FIRE 
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setFireTicks(Integer.MAX_VALUE);
		}
	},
	/** The entity will have its nametag visible to everyone */
	SHOW_NAME
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setCustomNameVisible(true);
		}
	},
	/** A lightning bolt is summoned upon spawning the monster. In addition,
	 *  if the monster has a hunt radius property, all nearby players within
	 *  the monster's set radius will be alerted of his presence in chat. */
	SPAWN_LIGHTNING
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			Location loc = entity.getLocation();
			entity.getWorld().strikeLightningEffect(loc);
			if (entityEquipment.containsStat(MonsterStat.HUNT_ON_SPAWN_RADIUS))
			{
				int huntRadius = entityEquipment.getStat(MonsterStat.HUNT_ON_SPAWN_RADIUS).intValue();
				List <Player> nearbyPlayers = ScanEntities.ScanNearbyPlayers(entity, (int) (huntRadius * 1.25));
				
				String message = ChatColor.GOLD + "You feel a great disturbance in the force...";
				if (entityEquipment instanceof CustomSpawnMessage)
				{
					message = ((CustomSpawnMessage) entityEquipment).getSpawnMessage();
				}
				
				for (Player p : nearbyPlayers)
				{
					p.sendMessage(message);
				}
			}
		}
	},
	/** Determines if the monster should automatically hunt a random nearby player upon spawning */
	HUNT_ON_SPAWN
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			if (entityEquipment.containsStat(MonsterStat.HUNT_ON_SPAWN_RADIUS))
			{
				int huntRadius = entityEquipment.getStat(MonsterStat.HUNT_ON_SPAWN_RADIUS).intValue();
				if (entity instanceof Monster)
				{
					List <Player> nearbyPlayersHunt = ScanEntities.ScanNearbyPlayers(entity, huntRadius);
					Monster mob = (Monster) entity;
					int n = nearbyPlayersHunt.size();
					if (!nearbyPlayersHunt.isEmpty())
					{
						Player p = nearbyPlayersHunt.get(new Random ().nextInt(n));
						mob.setTarget(p);
						p.sendMessage(ChatColor.DARK_RED + "You are being hunted...");
					}
				}
			}
		}
	},
	/** Displays a boss bar for this custom mob */
	BOSS_BAR
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			bossBarController.createBossBar(entity);
		}
	},
	/** When enabled, the monster will never lose sight of its prey (meaning that it will never "forget" its enemy) */
	PERSISTENT_ATTACKER,
	/** When enabled, the monster will not attack any custom entity that are not apart of any faction,
	 *  even if those monsters are in its hitlist. This flag does not apply to player */
	IGNORE_NON_FACTION_ENTITIES,
	/** When enabled, the mob will spawn a small fireworks show upon death */
	FIREWORK_DEATH,
	/** When enabled, entities will naturally despawn upon chunk unloads regardless if the monster is naturally non-hostile or not */
	DISABLE_PERSISTENCE
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setRemoveWhenFarAway(true);
		}
	},
	/** When enabled, entities will not naturally despawn upon chunk unloads */
	ENABLE_PERSISTENCE
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setRemoveWhenFarAway(false);
		}
	},
	/** When enabled, a random firework will be added into the entity's offhand upon spawning */
	RANDOM_FIREWORK_ON_OFFHAND
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.getEquipment().setItemInOffHand(new FireworkTypes().getRandomFirework(32, 2));
			entity.getEquipment().setItemInOffHandDropChance(entityEquipment.getDropRates()[5]);
		}
	},
	/** When enabled, the entity will no longer be able to naturally despawn once any player rides this tameable custom mob.
	 *  Otherwise, it will naturally despawn once out of sight. */
	ENABLE_PERSISTENCE_UPON_RIDING
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setRemoveWhenFarAway(true);
		}
	},
	/** When enabled, the mob will be immune to suffocation damage */
	DISABLE_SUFFOCATION_DAMAGE
	{
		@Override
		public void loadCustomSkills (List <AbstractSkill> skills)
		{
			skills.add(new DisableSuffocationSkill ());
		}
	},
	/** When enabled, the mob will finally shut up. */
	MAKE_SILENT
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setSilent(true);
		}
	},
	/** When enabled, the mob is no longer able to pick up dropped gear or weapons */
	DISABLE_PICK_UP_ITEMS
	{
		@Override
		public void applyCustomAttributes (LivingEntity entity, MobEquipment entityEquipment, BossBarController bossBarController)
		{
			entity.setCanPickupItems(false);
		}
	},
	/** When enabled, the entity is unable to be healed from healing or harming potions */
	DISABLE_MAGIC_HEALING
	{
		@Override
		public void loadCustomSkills(List<AbstractSkill> skills) 
		{
			skills.add(new DisableMagicHealSkill ());
		}
	},
	/** When enabled, the entity will not drop anything when dead */
	DISABLE_MOB_DROPS,
	/** When enabled, the entity will no longer melt to death */
	DISABLE_MELTING
	{
		@Override
		public void loadCustomSkills (List <AbstractSkill> skills)
		{
			skills.add(new DisableMeltingSkill ());
		}
	},
	/** When enabled, the entity will no longer drown to death */
	DISABLE_DROWNING
	{
		@Override
		public void loadCustomSkills (List <AbstractSkill> skills)
		{
			skills.add(new DisableDrowningSkill());
		}
	},
	/** When enabled, entities with this flag on will not grief any blocks when set to explode */
	DISABLE_EXPLOSION_GRIEFING
	;
	
	/** Does nothing when the mobflag does not have a custom definition specific for that attribute */
	@Override
	public void applyCustomAttributes(LivingEntity entity, MobEquipment entityEquipment,
			BossBarController bossBarController) 
	{
		// Do nothing
	}
	
	/** Does nothing when no custom skill function is assigned to this flag */
	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		
	}
}
