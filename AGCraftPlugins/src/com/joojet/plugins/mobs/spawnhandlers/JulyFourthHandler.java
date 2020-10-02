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
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.phantom.julyfourth.FireworkPhantom;
import com.joojet.plugins.mobs.monsters.pillager.julyfourth.PatrioticPillagerTypes;
import com.joojet.plugins.mobs.monsters.skeleton.julyfourth.PatrioticSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.julyfourth.PatrioticZombieTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class JulyFourthHandler extends AbstractSpawnHandler
{
	/** Used to generate random fireworks */
	private FireworkTypes fwTypes;
	
	public JulyFourthHandler (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		super (monsterTypeInterpreter, bossBarController);
		this.fwTypes = new FireworkTypes ();
		this.addMonsterTypes(new PatrioticZombieTypes(this.monsterTypeInterpreter), 
				new PatrioticSkeletonTypes(this.monsterTypeInterpreter),
				new PatrioticPillagerTypes(this.monsterTypeInterpreter));
	}
	
	/** Handles 4th of july mob spawns */
	public void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll)
	{
		// Insta kill phantoms and let them explode
		if (type == EntityType.PHANTOM)
		{
			this.transformFireworkPhantom(entity);
			return;
		}
		
		// Summon a new patriotic zombie when roll is between a certain range
		if (!reason.equals(SpawnReason.RAID) && roll >= 0.30 && roll <= 0.50)
		{
			MobEquipment mobEquipment = this.getRandomEqipment(type, biome);
			if (mobEquipment != null)
			{
				EquipmentTools.equipEntity(entity, mobEquipment, this.bossBarController);
			}
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
