package com.joojet.plugins.mobs.skills.visual;

import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveOnDeath;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

/**
 * Spawns a random firework on the entity's location on death. Also, entities wielding this skill
 * will have a subtle firework trail effect when moving around the world.
 */
public class FireworkExplosionDeath extends AbstractVisualSkill implements PassiveOnDeath
{
	@Override
	public boolean handleDeathEvent(LivingEntity entity, MobEquipment deadEntityEquipment) 
	{
		FireworkTypes fwTypes = new FireworkTypes ();
		Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
		ItemStack fwItem = fwTypes.getRandomFirework(1, 0);
		firework.setFireworkMeta((FireworkMeta)fwItem.getItemMeta());
		firework.detonate();
		return true;
	}

	@Override
	protected void displayVisualEffects(LivingEntity caster) 
	{
		if (this.random.nextDouble() <= 0.40)
		{
			ParticleUtil.spawnColoredParticlesOnEntity(caster, 4, 0, 0, 0, Particle.FIREWORKS_SPARK);
		}
	}
	
}
