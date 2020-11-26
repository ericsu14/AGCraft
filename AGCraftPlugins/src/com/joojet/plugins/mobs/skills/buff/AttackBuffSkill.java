package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.MonsterClassifier;

public class AttackBuffSkill extends AbstractBuffSkill 
{
	
	/** A monster skill that applies a strength I buff to the caster itself and its
	 *  surrounding allies. */
	public AttackBuffSkill ()
	{
		super (30, 300, 1, 8);
	}
	
	/** Gives the caster and any of its surrounding allies a Strength I buff. */
	@Override
	protected void handleSkill(LivingEntity caster, ArrayList<LivingEntity> allies, ArrayList<LivingEntity> enemies) 
	{
		this.spawnColoredParticlesOnEntity(caster, 30, 128, 0, 0, Particle.SPELL_MOB);
		this.applyPotionEffect(caster, PotionEffectType.INCREASE_DAMAGE, 6000, 0);
		caster.getWorld().playSound(caster.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0F, 1.0F);
		
		/** Apply buffs on allies a half second later */
		new BukkitRunnable () {
			@Override
			public void run ()
			{
				for (LivingEntity ally : allies)
				{
					spawnColoredParticlesOnEntity(ally, 30, 128, 0, 0, Particle.SPELL_MOB);
					applyPotionEffect(ally, PotionEffectType.INCREASE_DAMAGE, 6000, 0);
				}
			}
		}.runTaskLater(AGCraftPlugin.plugin, 30);
	}
	
	/** Only use this skill if any surrounding player's threat score exceeds mythic level */
	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) 
	{
		return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}

}
