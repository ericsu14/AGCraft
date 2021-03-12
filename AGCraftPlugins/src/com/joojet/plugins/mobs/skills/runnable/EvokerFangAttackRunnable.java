package com.joojet.plugins.mobs.skills.runnable;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftMob;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.EntityEvokerFangs;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EnumDirection;
import net.minecraft.server.v1_16_R3.IBlockData;
import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.VoxelShape;

public class EvokerFangAttackRunnable extends BukkitRunnable
{
	/** The caster using this skill */
	protected LivingEntity caster;
	/** The target being attacked by the skill */
	protected LivingEntity target;
	/** NMS instance of the caster */
	protected EntityInsentient casterNMS;
	
	/** Launches an evoker-fang attack in the world
	 *  @param caster Entity casting this skill
	 *  @param target Target attacked by this skill */
	public EvokerFangAttackRunnable (LivingEntity caster, LivingEntity target)
	{
		this.caster = caster;
		this.target = target;
		this.casterNMS = ((CraftMob) caster).getHandle();
	}
	
	/** Launches a fang attack */
	@Override
	public void run() 
	{
		this.prepareFangs();
	}
	
	/** Prepares evoker fang attack by calculating points of attack. Code is recreated line-by-line from the evoker fang attack from the
	 *  NMS codebase, but reverse engineered to be used by any living entity.  */
	public void prepareFangs ()
	{
		Location targetLocation = target.getLocation();
		Location casterLocation = caster.getLocation();
		
		double var1 = Math.min(targetLocation.getBlockY(), casterLocation.getBlockY());
		double var3 = Math.max(targetLocation.getBlockY(), casterLocation.getBlockY()) + 1.0D;
		float var5 = (float) MathHelper.d(targetLocation.getBlockZ() - casterLocation.getBlockZ(), targetLocation.getBlockX() - casterLocation.getBlockX());
		
		if (caster.getBoundingBox().clone().expand(3.0D).contains(targetLocation.toVector()))
		{
			int var6;
			
			for (var6 = 0; var6 < 5; var6++)
			{
				float var7 = var5 + var6 * (float) Math.PI * 0.4F;
				this.summonFangs(casterLocation.getBlockX() + MathHelper.cos(var7) * 1.5D, casterLocation.getBlockZ() + MathHelper.sin(var7) * 1.5D, var1, var3, var7, 0, casterLocation.getWorld());
			}
			for (var6 = 0; var6 < 8; var6++)
			{
				float var7 = var5 + var6 + (float) Math.PI * 2.0F / 8.0F + 1.2566371F;
				this.summonFangs(casterLocation.getBlockX() + MathHelper.cos(var7) * 2.5D, casterLocation.getBlockZ() + MathHelper.sin(var7) * 2.5D, var1, var3, var7, 3, casterLocation.getWorld());
			}
		}
		
		else
		{
			for (int var6 = 0; var6 < 16; var6++)
			{
				double var7 = 1.25D * (var6 + 1);
				int var9 = 1 * var6;
				summonFangs (casterLocation.getBlockX() + MathHelper.cos(var5) * var7, casterLocation.getBlockZ() + MathHelper.sin(var5) * var7, var1, var3, var5, var9, casterLocation.getWorld());
			}
		}
	}
	
	/** Summons fangs on a location. Code is recreated line-by-line from the NMS codebase. */
	public void summonFangs (double var0, double var2, double var4, double var6, float var8, int var9, World world)
	{
		BlockPosition var10 = new BlockPosition (var0, var6, var2);
		boolean var11 = false;
		double var12 = 0.0D;
		do
		{
			BlockPosition var14 = var10.down();
			IBlockData var15 = this.casterNMS.world.getType(var14);
			if (var15.d (this.casterNMS.world, var14, EnumDirection.UP))
			{
				if (!this.casterNMS.world.isEmpty (var10))
				{
					IBlockData var16 = this.casterNMS.world.getType(var10);
					VoxelShape var17 = var16.getCollisionShape(this.casterNMS.world, var10);
					if (!var17.isEmpty())
					{
						var12 = var17.c(EnumDirection.EnumAxis.Y);
					}
				}
				var11 = true;
				break;
			}
			var10 = var10.down();
		} while (var10.getY() >= MathHelper.floor(var4) - 1);
		
		if (var11)
		{
			this.casterNMS.world.addEntity(new EntityEvokerFangs (this.casterNMS.world, var0, var10.getY() + var12, var2, var8, var9, this.casterNMS));
		}
	}
}
