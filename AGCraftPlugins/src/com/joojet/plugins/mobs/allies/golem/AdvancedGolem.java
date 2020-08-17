package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class AdvancedGolem extends MobEquipment
{
	public AdvancedGolem ()
	{
		super (MonsterType.ADVANCED_GOLEM);
		this.addBiomes(Biome.THE_VOID);
		
		this.name = "Advanced Golem";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 200;
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.SNOWMAN);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.SPEED,
				CustomPotionEffect.STRENGTH);
	}
}
