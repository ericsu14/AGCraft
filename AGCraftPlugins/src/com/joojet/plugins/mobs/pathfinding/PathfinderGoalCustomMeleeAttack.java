package com.joojet.plugins.mobs.pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.util.Mth;

import org.bukkit.craftbukkit.v1_18_R1.entity.CraftLivingEntity;

public class PathfinderGoalCustomMeleeAttack extends MeleeAttackGoal 
{
	/** Mob equipment instance tied to the creature (used to extract attack damage attributes) */
	protected MobEquipment mobEquipment;
	
	/** Creates a custom melee attack allowing passive monsters to peform basic attacks */
	public PathfinderGoalCustomMeleeAttack(PathfinderMob creature, MobEquipment mobEquipment) 
	{
		super(creature, 1.0D, true);
		this.mobEquipment = mobEquipment;
	}
	
	/** Overrides the attacking behavior to use our custom attackEntity code */
	@Override
	protected void checkAndPerformAttack (net.minecraft.world.entity.LivingEntity target, double var1)
	{
		double attackRadius = this.getAttackReachSqr(target);
		if (var1 <= attackRadius && this.getTicksUntilNextAttack() <= 0)
		{
			this.resetAttackCooldown();
			this.mob.swing(InteractionHand.MAIN_HAND);
			attackEntity(this.mob, this.mobEquipment, target);
		}
	}
	
	/** Extends the mob's attack radius if that mob is a chicken, due to its smaller hitbox. */
	@Override
	protected double getAttackReachSqr(net.minecraft.world.entity.LivingEntity var0) 
	{
		float multiplier = 2.0F;
		if (this.mob instanceof Chicken)
		{
			multiplier += 1.5F;
		}
		return (this.mob.getBbWidth() * multiplier * this.mob.getBbWidth() * multiplier + var0.getBbWidth());
	}
	
	/** Uses the modified attackEntity implementation for bukkit-based living entities.
	 *  @param attacker Entity attacking the target
	 *  @param attackerEquipment Attacker's mobEquipment instance
	 *  @param target Target being attacked by the entity */
	public static boolean attackEntity (LivingEntity attacker, MobEquipment attackerEquipment, LivingEntity target)
	{
		return attackEntity ((PathfinderMob) ((CraftLivingEntity) attacker).getHandle(), attackerEquipment, ((CraftLivingEntity) target).getHandle());			
	}
	
	/** A modified implementation of the NMS attack entity code that supports custom base damage
	 *  and knockback strength attributes found in our MobEquipment instances. This allows monsters
	 *  who do not naturally have these attributes to be capable of attacking monsters.
	 *  @param attacker Entity attacking the target
	 *  @param attackerEquipment Attacker's mobEquipment instance
	 *  @param target Target being attacked by the entity */
	public static boolean attackEntity (PathfinderMob attacker, MobEquipment attackerEquipment, Entity target)
	{
		float baseDamage = attackerEquipment.containsStat(MonsterStat.BASE_ATTACK_DAMAGE) ? 
				attackerEquipment.getStat(MonsterStat.BASE_ATTACK_DAMAGE).floatValue() : 4.0f;
		float knockBack = attackerEquipment.containsStat(MonsterStat.BASE_KNOCKBACK_STRENGTH) ? 
				attackerEquipment.getStat(MonsterStat.BASE_KNOCKBACK_STRENGTH).floatValue() : 0.1f;;
				
		// Amplifies attack damage with strength / weakness buffs
		if (attacker instanceof net.minecraft.world.entity.LivingEntity)
		{
			LivingEntity bukkitAttacker = (LivingEntity) (org.bukkit.entity.Entity) attacker.getBukkitEntity();
			if (bukkitAttacker.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
			{
				baseDamage += (bukkitAttacker.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + 1) * 4;
			}
			if (bukkitAttacker.hasPotionEffect(PotionEffectType.WEAKNESS))
			{
				baseDamage -= (bukkitAttacker.getPotionEffect(PotionEffectType.WEAKNESS).getAmplifier() + 1) * 4;
			}
		}
		
		
		if (target instanceof net.minecraft.world.entity.LivingEntity)
		{
			baseDamage += EnchantmentHelper.getDamageBonus(attacker.getMainHandItem(), ((net.minecraft.world.entity.LivingEntity)target).getMobType());
			knockBack += EnchantmentHelper.getKnockbackBonus(attacker);
		}
		
		// Handles fire aspect enchants
		int fireAspectLevel = EnchantmentHelper.getFireAspect(attacker);
		if (fireAspectLevel > 0) 
		{
			EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((org.bukkit.entity.Entity)attacker.getBukkitEntity(), 
					(org.bukkit.entity.Entity)target.getBukkitEntity(), fireAspectLevel * 4);
			Bukkit.getPluginManager().callEvent((Event)combustEvent);
			if (!combustEvent.isCancelled())
			{
		        target.setSecondsOnFire(combustEvent.getDuration(), false);
			}
		}
		
		boolean flag = target.hurt(DamageSource.mobAttack(attacker), baseDamage);
		if (flag)
		{
			if (knockBack > 0.0F && target instanceof net.minecraft.world.entity.LivingEntity)
			{
				((net.minecraft.world.entity.LivingEntity) target).knockback(knockBack * 0.5f, Mth.sin(attacker.getBukkitYaw() * 0.017453292F), 
						-Mth.cos(attacker.getBukkitYaw() * 0.017453292F));
				attacker.setDeltaMovement(attacker.getDeltaMovement().multiply(0.6D, 1.0F, 0.6D));
			}
			attacker.doEnchantDamageEffects(attacker, target);
			attacker.setLastHurtMob(target);
		}
		
		return flag;
	}

}
