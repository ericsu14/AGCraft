package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.phantom.julyfourth.FireworkPhantom;
import com.joojet.plugins.mobs.monsters.phantom.julyfourth.JulyFourthPhantomTypes;
import com.joojet.plugins.mobs.monsters.pillager.julyfourth.PatrioticPillagerTypes;
import com.joojet.plugins.mobs.monsters.skeleton.julyfourth.PatrioticSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.julyfourth.PatrioticZombieTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class JulyFourthHandler extends AbstractSpawnHandler
{
	/** The key used to reference this handler's spawn chance variable from the config file*/
	public static final String JULY_FOURTH_HANDLER_KEY = "july-fourth-spawn-chance";
	/** Used to generate random fireworks */
	private FireworkTypes fwTypes;
	
	public JulyFourthHandler (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		super (monsterTypeInterpreter, bossBarController, JULY_FOURTH_HANDLER_KEY);
		this.fwTypes = new FireworkTypes ();
		this.addMonsterTypes(new PatrioticZombieTypes(this.monsterTypeInterpreter), 
				new PatrioticSkeletonTypes(this.monsterTypeInterpreter),
				new PatrioticPillagerTypes(this.monsterTypeInterpreter),
				new JulyFourthPhantomTypes (this.monsterTypeInterpreter));
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.SPAWNER_EGG, SpawnReason.REINFORCEMENTS,
				SpawnReason.DEFAULT, SpawnReason.DISPENSE_EGG, SpawnReason.LIGHTNING, SpawnReason.PATROL,
				SpawnReason.TRAP);
	}
	
	/** Handles 4th of july mob spawns */
	public void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome)
	{
		// Insta kill phantoms and let them explode
		if (type == EntityType.PHANTOM)
		{
			this.transformFireworkPhantom(entity);
			return;
		}
		
		// Summon a new patriotic zombie when roll is between a certain range
		if (this.canSpawn(reason))
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
	}
	
	/** Transforms the phantom into a firework phantom */
	public void transformFireworkPhantom (LivingEntity entity)
	{
		EquipmentTools.equipEntity(entity, new FireworkPhantom(), this.bossBarController);
		Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
		ItemStack fwItem = fwTypes.getRandomFirework(1, 0);
		firework.setFireworkMeta((FireworkMeta)fwItem.getItemMeta());
		firework.detonate();
	}

}
