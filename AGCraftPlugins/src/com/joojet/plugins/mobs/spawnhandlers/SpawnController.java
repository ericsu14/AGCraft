package com.joojet.plugins.mobs.spawnhandlers;

import java.util.EnumMap;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.interpreter.ThemedServerEventInterpreter;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;
import com.joojet.plugins.agcraft.config.ServerConfigFile;

public class SpawnController 
{
	/** Server mode specific spawn handlers */
	protected EnumMap <ServerMode, AbstractSpawnHandler> serverModeSpawnHandlers;
	/** Themed server event specific spawn handlers */
	protected EnumMap <ThemedServerEvent, AbstractSpawnHandler> themedEventSpawnHandlers;
	/** Reference to the amplified mob spawner instance using this class */
	protected AmplifiedMobSpawner amplifiedMobSpawner;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Stores the command interpreter used for server event types */
	protected ThemedServerEventInterpreter serverEventInterpreter;
	/** A reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	/** Mob Skill runnable used to control our custom skill system */
	protected MobSkillRunner mobSkillRunner;
	/** Search trie used to initialize custom summoning scroll instances */
	protected SummoningScrollInterpreter summonTypeInterpreter;
	
	
	public SpawnController (AmplifiedMobSpawner amplifiedMobSpawner, MonsterTypeInterpreter monsterTypeInterpreter, 
			SummoningScrollInterpreter summonTypeInterpreter, BossBarController bossBarController,
			MobSkillRunner mobSkillRunner)
	{
		this.serverModeSpawnHandlers = new EnumMap <ServerMode, AbstractSpawnHandler> (ServerMode.class);
		this.themedEventSpawnHandlers = new EnumMap <ThemedServerEvent, AbstractSpawnHandler> (ThemedServerEvent.class);
		this.amplifiedMobSpawner = amplifiedMobSpawner;
		this.summonTypeInterpreter = summonTypeInterpreter;
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.bossBarController = bossBarController;
		this.mobSkillRunner = mobSkillRunner;
	}
	
	public void loadSpawnHandlers ()
	{
		// Sets up spawn event handlers
		this.addServerModeSpawnHandler(ServerMode.HUNGER_GAMES, 
				new HungerGamesHandler (this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController, this.mobSkillRunner));
		this.addServerModeSpawnHandler(ServerMode.UHC, 
				new UHCHandler(this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController, this.mobSkillRunner));
		this.addThemedServerEventHandler(ThemedServerEvent.DEFAULT, 
				new AmplifiedMobHandler(this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController, this.mobSkillRunner));
		this.addThemedServerEventHandler(ThemedServerEvent.BEAT_THE_BRUINS, 
				new BeatTheBruinsHandler (this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController, this.mobSkillRunner));
		this.addThemedServerEventHandler(ThemedServerEvent.JULY_FOURTH, 
				new JulyFourthHandler (this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController, this.mobSkillRunner));
	}
	
	public void addServerModeSpawnHandler (ServerMode mode, AbstractSpawnHandler handler)
	{
		this.serverModeSpawnHandlers.put(mode, handler);
	}
	
	public void addThemedServerEventHandler (ThemedServerEvent mode, AbstractSpawnHandler handler)
	{
		this.themedEventSpawnHandlers.put(mode, handler);
	}
	
	public void handleSpawnEvent (CreatureSpawnEvent event)
	{
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		LivingEntity entity = event.getEntity();
		Biome biome = entity.getLocation().getBlock().getBiome();
		
		ServerMode mode = AGCraftPlugin.plugin.serverMode;
		
		// Handle server mode specific spawn events
		if (mode != ServerMode.NORMAL)
		{
			if (this.serverModeSpawnHandlers.containsKey(mode))
			{
				this.serverModeSpawnHandlers.get(mode).createSpawnEventHandlerTask(entity, type, reason, biome);
			}
		}
		// Otherwise, handle normal server-based spawn events
		else
		{
			ThemedServerEvent themedEvent = this.amplifiedMobSpawner.getThemedServerEvent();
			if (this.themedEventSpawnHandlers.containsKey(themedEvent))
			{
				this.themedEventSpawnHandlers.get(themedEvent).createSpawnEventHandlerTask(entity, type, reason, biome);;
			}
			if (themedEvent != ThemedServerEvent.DEFAULT && 
					this.themedEventSpawnHandlers.containsKey(ThemedServerEvent.DEFAULT))
			{
				this.themedEventSpawnHandlers.get(ThemedServerEvent.DEFAULT).createSpawnEventHandlerTask(entity, type, reason, biome);
			}
		}
	}
	
	/** Loads spawn chances for each unique spawn handler from the server config. file */
	public void loadSpawnChances (ServerConfigFile config)
	{
		for (AbstractSpawnHandler handler : this.serverModeSpawnHandlers.values())
		{
			handler.getSpawnChanceFromConfigFile(config);
		}
		
		for (AbstractSpawnHandler handler : this.themedEventSpawnHandlers.values())
		{
			handler.getSpawnChanceFromConfigFile(config);
		} 
	}
	
}
