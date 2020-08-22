package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.head.ScruffyFace;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class Scruffy extends MobEquipment
{
	public Scruffy ()
	{
		super (MonsterType.SCRUFFY);
		this.addBiomes(Biome.THE_VOID);
		this.name = "Scruffy";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.SNOWMAN, EntityType.IRON_GOLEM);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.WATER_BREATHING,
				CustomPotionEffect.FIRE_RESISTANCE, CustomPotionEffect.REGEN, CustomPotionEffect.SPEED);
		
		// Chestplate
		this.helmet = new ScruffyFace (this.color);
		
		// Boots
		this.boots = new LetItGo (this.color);
	}
		
}
