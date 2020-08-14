package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.phantom.julyfourth.FireworkPhantom;
import com.joojet.plugins.mobs.monsters.pillager.julyfourth.PatrioticPillager;
import com.joojet.plugins.mobs.monsters.skeleton.julyfourth.PatrioticSkeleton;
import com.joojet.plugins.mobs.monsters.zombie.julyfourth.PatrioticZombie;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class JulyFourthHandler extends AmplifiedSpawnHandler
{
	/** Used to generate random fireworks */
	private FireworkTypes fwTypes;
	
	public JulyFourthHandler ()
	{
		this.fwTypes = new FireworkTypes ();
	}
	
	/** Handles 4th of july mob spawns */
	public void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll)
	{
		// Insta kill phantoms and let them explode
		if (type.equals(EntityType.PHANTOM))
		{
			this.transformFireworkPhantom(entity);
			return;
		}
		
		// Summon a new patriotic zombie when roll is between a certain range
		if (!reason.equals(SpawnReason.RAID) && roll >= 0.30 && roll <= 0.50)
		{
			MobEquipment mobEquipment;
			switch (type)
			{
				case ZOMBIE:
					mobEquipment = new PatrioticZombie();
					break;
				case HUSK:
					mobEquipment = new PatrioticZombie();
					break;
				case SKELETON:
					mobEquipment = new PatrioticSkeleton();
					break;
				case PILLAGER:
					mobEquipment = new PatrioticPillager();
					break;
				default:
					return;
 			}
			EquipmentTools.equipEntity(entity, mobEquipment);
		}
	}
	
	/** Transforms the phantom into a firework phantom */
	public void transformFireworkPhantom (LivingEntity entity)
	{
		EquipmentTools.equipEntity(entity, new FireworkPhantom());
		Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
		ItemStack fwItem = fwTypes.getRandomFirework(1, 0);
		firework.setFireworkMeta((FireworkMeta)fwItem.getItemMeta());
		firework.detonate();
	}

	@Override
	public boolean reasonFilter(SpawnReason reason) 
	{
		return true;
	}
}
