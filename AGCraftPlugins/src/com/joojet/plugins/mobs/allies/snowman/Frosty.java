package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.head.FrostyFace;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class Frosty extends MobEquipment
{
	public Frosty ()
	{
		super (MonsterType.FROSTY_THE_SNOWMAN);
		this.addBiomes(Biome.THE_VOID);
		this.name = "Frosty the Snowman";
		this.color = ChatColor.AQUA;
		this.health = 30;
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.SNOWMAN, EntityType.IRON_GOLEM);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, 
				CustomPotionEffect.WATER_BREATHING, 
				CustomPotionEffect.FIRE_RESISTANCE, 
				CustomPotionEffect.REGEN);
		
		// Chestplate
		this.helmet = new FrostyFace (this.color);
	}
}
