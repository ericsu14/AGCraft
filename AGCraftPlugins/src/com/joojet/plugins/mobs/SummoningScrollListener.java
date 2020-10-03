package com.joojet.plugins.mobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.metadata.SummonedMetadata;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.mobs.util.EquipmentTools;


public class SummoningScrollListener extends AGListener
{
	// Interpreter to search for used summoning scrolls
	private SummoningScrollInterpreter summonInterpreter;
	
	/** Stores a reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	
	public SummoningScrollListener (BossBarController bossBarController)
	{
		this.bossBarController = bossBarController;
		this.summonInterpreter = new SummoningScrollInterpreter();
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Checks for use of a summoning scroll */
	@EventHandler
	public void useSummoningScroll (PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& event.getItem() != null)
		{
			ItemStack item = event.getItem();
			ItemMeta itemMeta = item.getItemMeta();
			if (SummoningScroll.isSummoningScroll(item))
			{
				SummonTypes scrollType = this.summonInterpreter.searchTrie(itemMeta.getLocalizedName());
				if (scrollType != null)
				{
					SummoningScroll scroll = scrollType.getSummon();
					
					// Gets player's current location
					Location spawnLocation = p.getEyeLocation();
					
					// Checks if the monster has a y-limit flag enabled.
					// If so, cancel the summoning scroll event if the player does not
					// have EquipmentTools.openAirRequirement blocks of clear space above him/her.
					if (scroll.getMob().containsStat(MonsterStat.Y_LIMIT)
							&& (!EquipmentTools.checkSpawnSpace(p)
									|| spawnLocation.getBlockY() < scroll.getMob().getStat(MonsterStat.Y_LIMIT)))
					{
						p.sendMessage(ChatColor.RED + "Error: Unable to summon the monster. Please check that there is at least "
								+ EquipmentTools.openAirRequirement + " blocks of air above your current location.");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
						return;
					}
		
					// Spawns the entity into the world in front of the player
					LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(spawnLocation, scroll.getMobType());
					
					// Attaches special metadata that identifies this entity as a summoned custom monster
					new SummonedMetadata (scroll.getMob().getName()).addStringMetadata(entity);
					
					// If the spawned entity is a golem, make him player built
					if (entity instanceof IronGolem)
					{
						IronGolem golem = (IronGolem) entity;
						golem.setPlayerCreated(true);
					}
					
					// If the spawned entity is a wolf, autotame him
					if (entity instanceof Wolf)
					{
						Wolf wolf = (Wolf) entity;
						wolf.setAdult();
						wolf.setTamed(true);
						wolf.setOwner(p);
						wolf.setCollarColor(scroll.getMob().getDyeColor());
					}
					
					EquipmentTools.equipEntity(entity, scroll.getMob(), this.bossBarController);
					
					// Entities are always persistent when summoned VIA summoning scroll
					entity.setRemoveWhenFarAway(false);
					
					p.sendMessage(ChatColor.AQUA + "Sucessfully summoned " + scroll.getMob().getChatColor() + scroll.getName() + ChatColor.AQUA + "!");
					p.playSound(spawnLocation, Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 1.0f, 1.0f);
					p.playSound(spawnLocation, Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f);
					p.spawnParticle(Particle.SPELL_INSTANT, spawnLocation, 10, 1.0, 1.0, 0.0, 0.1, null);
					p.spawnParticle(Particle.SPELL_INSTANT, spawnLocation, 15, 1.0, 1.0, 0.0, 0.1, null);
					int numScrolls = item.getAmount();
					
					// Dec. or wither away summoning scroll
					if (numScrolls > 1)
					{
						item.setAmount(numScrolls - 1); 
					}
					else
					{
						// Marks the scroll as used (since removing it has a chance of crashing the world)
						item.setItemMeta(SummoningScroll.markUsed(itemMeta));
					}
				}
			}
		}
	}

	@Override
	public void loadConfigVarialbes(ServerConfigFile config) 
	{

	}
}
