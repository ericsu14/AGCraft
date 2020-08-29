package com.joojet.plugins.mobs.util.customtargets;

import java.util.EnumSet;

import net.minecraft.server.v1_16_R2.EntityGiantZombie;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.MathHelper;
import net.minecraft.server.v1_16_R2.PathfinderGoal;
import net.minecraft.server.v1_16_R2.Vec3D;

public class PathfinderGoalGiantMoveTarget extends PathfinderGoal {
    private final EntityGiantZombie a;
    
    public PathfinderGoalGiantMoveTarget(EntityGiantZombie entityghast) 
    {
      this.a = entityghast;
      a(EnumSet.of(PathfinderGoal.Type.LOOK));
    }
    
    public boolean a() {
      return true;
    }
    
    public void e() {
      if (this.a.getGoalTarget() == null) {
        Vec3D vec3d = this.a.getMot();
        this.a.yaw = -((float)MathHelper.d(vec3d.x, vec3d.z)) * 57.295776F;
        this.a.aA = this.a.yaw;
      } else {
        EntityLiving entityliving = this.a.getGoalTarget();
        // double d0 = 64.0D;
        if (entityliving.h(this.a) < 4096.0D) {
          double d1 = entityliving.locX() - this.a.locX();
          double d2 = entityliving.locZ() - this.a.locZ();
          this.a.yaw = -((float)MathHelper.d(d1, d2)) * 57.295776F;
          this.a.aA = this.a.yaw;
        } 
      } 
    }
  }
