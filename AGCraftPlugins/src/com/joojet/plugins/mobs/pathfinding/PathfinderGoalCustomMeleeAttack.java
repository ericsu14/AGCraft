package com.joojet.plugins.mobs.pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;

import net.minecraft.server.v1_16_R3.DamageSource;
import net.minecraft.server.v1_16_R3.EnchantmentManager;
import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.EntityChicken;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EnumHand;
import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.PathfinderGoalMeleeAttack;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;

public class PathfinderGoalCustomMeleeAttack extends PathfinderGoalMeleeAttack 
{
	/** Mob equipment instance tied to the creature (used to extract attack damage attributes) */
	protected MobEquipment mobEquipment;
	
	/** Creates a custom melee attack allowing passive monsters to peform basic attacks */
	public PathfinderGoalCustomMeleeAttack(EntityCreature creature, MobEquipment mobEquipment) 
	{
		super(creature, 1.0D, true);
		this.mobEquipment = mobEquipment;
	}
	
	/** Overrides the attacking behavior to use our custom attackEntity code */
	@Override
	protected void a (EntityLiving target, double var1)
	{
		double attackRadius = a(target);
		if (var1 <= attackRadius && this.h())
		{
			this.g();
			this.a.swingHand(EnumHand.MAIN_HAND);
			attackEntity(this.a, this.mobEquipment, target);
		}
	}
	
	/** Extends the mob's attack radius if that mob is a chicken, due to its smaller hitbox. */
	@Override
	protected double a(EntityLiving var0) 
	{
		float multiplier = 2.0F;
		if (this.a instanceof EntityChicken)
		{
			multiplier += 1.5F;
		}
		return (this.a.getWidth() * multiplier * this.a.getWidth() * multiplier + var0.getWidth());
	}
	
	/** Uses the modified attackEntity implementation for bukkit-based living entities.
	 *  @param attacker Entity attacking the target
	 *  @param attackerEquipment Attacker's mobEquipment instance
	 *  @param target Target being attacked by the entity */
	public static boolean attackEntity (LivingEntity attacker, MobEquipment attackerEquipment, LivingEntity target)
	{
		return attackEntity ((EntityInsentient) ((CraftLivingEntity) attacker).getHandle(), attackerEquipment, ((CraftLivingEntity) target).getHandle());			
	}
	
	/** A modified implementation of the NMS attack entity code that supports custom base damage
	 *  and knockback strength attributes found in our MobEquipment instances. This allows monsters
	 *  who do not naturally have these attributes to be capable of attacking monsters.
	 *  @param attacker Entity attacking the target
	 *  @param attackerEquipment Attacker's mobEquipment instance
	 *  @param target Target being attacked by the entity */
	public static boolean attackEntity (EntityInsentient attacker, MobEquipment attackerEquipment, Entity target)
	{
		float baseDamage = attackerEquipment.containsStat(MonsterStat.BASE_ATTACK_DAMAGE) ? 
				attackerEquipment.getStat(MonsterStat.BASE_ATTACK_DAMAGE).floatValue() : 4.0f;
		float knockBack = attackerEquipment.containsStat(MonsterStat.BASE_KNOCKBACK_STRENGTH) ? 
				attackerEquipment.getStat(MonsterStat.BASE_KNOCKBACK_STRENGTH).floatValue() : 0.1f;;
				
		// Amplifies attack damage with strength / weakness buffs
		if (attacker instanceof EntityLiving)
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
		
		
		if (target instanceof EntityLiving)
		{
			baseDamage += EnchantmentManager.a(attacker.getItemInMainHand(), ((EntityLiving)target).getMonsterType());
			knockBack += EnchantmentManager.b(attacker);
		}
		
		// Handles fire aspect enchants
		int fireAspectLevel = EnchantmentManager.getFireAspectEnchantmentLevel(attacker);
		if (fireAspectLevel > 0) 
		{
			EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((org.bukkit.entity.Entity)attacker.getBukkitEntity(), 
					(org.bukkit.entity.Entity)target.getBukkitEntity(), fireAspectLevel * 4);
			Bukkit.getPluginManager().callEvent((Event)combustEvent);
			if (!combustEvent.isCancelled())
			{
		        target.setOnFire(combustEvent.getDuration(), false);
			}
		}
		
		boolean flag = target.damageEntity(DamageSource.mobAttack(attacker), baseDamage);
		if (flag)
		{
			if (knockBack > 0.0F && target instanceof EntityLiving)
			{
				((EntityLiving) target).a(knockBack * 0.5f, MathHelper.sin(attacker.yaw * 0.017453292F), 
						-MathHelper.cos(attacker.yaw * 0.017453292F));
				attacker.setMot(attacker.getMot().d(0.6D, 1.0F, 0.6D));
			}
			attacker.a(attacker, target);
			attacker.z(target);
		}
		
		return flag;
	}

}
