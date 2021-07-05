package com.joojet.plugins.mobs.skills.visual;

import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveOnExplode;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

/** Enables fourth of july creepers to explode in a ball of fireworks */
public class FireworkCreeperExplosionDeath extends AbstractVisualSkill implements PassiveOnExplode 
{
	@Override
	public boolean handleExplosionEvent(LivingEntity entity, MobEquipment entityEquipment) 
	{
		FireworkTypes fwTypes = new FireworkTypes ();
		Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getEyeLocation().add(0, 1, 0), EntityType.FIREWORK);
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
