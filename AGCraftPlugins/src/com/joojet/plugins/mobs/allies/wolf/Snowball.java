package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.chest.SnowballHeart;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class Snowball extends MobEquipment
{
	public Snowball ()
	{
		super (MonsterType.SNOWBALL);
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.AQUA;
		this.name = "Snowball";
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		this.chestplate = new SnowballHeart (this.color);
		this.boots = new LetItGo (this.color);
		this.setDropRates(0.0f, 1.15f, 0.0f, 0.0f, 0.0f, 0.0f);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.SNOWMAN, EntityType.IRON_GOLEM);
	}
}
