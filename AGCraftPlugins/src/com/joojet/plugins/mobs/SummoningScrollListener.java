package com.joojet.plugins.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.metadata.SummonedMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.mobs.util.EquipmentTools;


public class SummoningScrollListener extends AGListener
{
	// Interpreter to search for used summoning scrolls
	private SummoningScrollInterpreter summonInterpreter;
	
	/** Stores a reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	
	
	public SummoningScrollListener (SummoningScrollInterpreter summonTypeInterpreter, BossBarController bossBarController)
	{
		this.bossBarController = bossBarController;
		this.summonInterpreter = summonTypeInterpreter;
	}
	
	@Override
	public void onEnable ()
	{
		
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
				// Attempts to get the summon ID from the summoning scroll
				SummoningScroll scroll = null;
				SummonedMetadata summonMetadata = new SummonedMetadata();
				if (summonMetadata.getStringMetadata(itemMeta) != null)
				{
					scroll = this.summonInterpreter.searchTrie(summonMetadata.getStringMetadata(itemMeta));
				}
				else if (itemMeta.hasLocalizedName())
				{
					scroll = this.summonInterpreter.searchTrie(itemMeta.getLocalizedName());
				}
				
				if (scroll != null)
				{	
					if (!summonMonster (p, scroll.getMob(), scroll.getMobType(), this.bossBarController, false))
					{
						return;
					}
					
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
	
	/** Summons a custom monster at the player's current location
	 *  @param player - Player summoning the monster
	 *  @param mob - MobEquipment instance of the monster being summoned
	 *  @param entityType - The summoned monster's entity type
	 *  @param bossBarController - Instance to the server's active boss bar controller
	 *  @param silent - Determines whatever or not if the summon effects are played upon spawning in a mob */
	public static boolean summonMonster (Player player, MobEquipment mob, EntityType entityType, BossBarController bossBarController, boolean silent)
	{
		// Gets player's current location
		Location spawnLocation = player.getEyeLocation();
		
		// Checks if the monster has a y-limit flag enabled.
		// If so, cancel the summoning scroll event if the player does not
		// have EquipmentTools.openAirRequirement blocks of clear space above him/her.
		if (mob.containsStat(MonsterStat.Y_LIMIT)
				&& (!EquipmentTools.checkSpawnSpace(player)
						|| spawnLocation.getBlockY() < mob.getStat(MonsterStat.Y_LIMIT)))
		{
			player.sendMessage(ChatColor.RED + "Error: Unable to summon the monster. Please check that there is at least "
					+ EquipmentTools.openAirRequirement + " blocks of air above your current location.");
			player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
			return false;
		}

		// Spawns the entity into the world in front of the player
		LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(spawnLocation, entityType);
		
		// Attaches special metadata that identifies this entity as a summoned custom monster
		new SummonedMetadata (mob.toString()).addStringMetadata(entity);
		
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
			wolf.setOwner(player);
			wolf.setCollarColor(mob.getDyeColor());
		}
		
		EquipmentTools.equipEntity(entity, mob, bossBarController);
		
		// Entities are always persistent when summoned VIA summoning scroll
		entity.setRemoveWhenFarAway(false);
		
		if (!silent)
		{
			player.sendMessage(ChatColor.AQUA + "Sucessfully summoned " + mob.getChatColor() + mob.getName() + ChatColor.AQUA + "!");
			player.playSound(spawnLocation, Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 1.0f, 1.0f);
			player.playSound(spawnLocation, Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f);
			player.spawnParticle(Particle.SPELL_INSTANT, spawnLocation, 10, 1.0, 1.0, 0.0, 0.1, null);
			player.spawnParticle(Particle.SPELL_INSTANT, spawnLocation, 15, 1.0, 1.0, 0.0, 0.1, null);
		}
		return true;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{

	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}
}
