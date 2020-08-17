package com.joojet.plugins.mobs.monsters.phantom.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class PhantomMenace extends MobEquipment
{
	public PhantomMenace ()
	{
		super (MonsterType.PHANTOM_MENACE);
		this.addBiomes(Biome.THE_VOID);
		this.name = "The Phantom Menace";
		this.color = ChatColor.LIGHT_PURPLE;
		this.addFactions(Faction.PHANTOM);
		this.addRivalFactions(Faction.UCLA, Faction.USC);
		this.addTargetsToHitList(EntityType.SKELETON, EntityType.ZOMBIE, EntityType.SPIDER,
				EntityType.WITCH, EntityType.CREEPER, EntityType.CAVE_SPIDER, EntityType.ZOMBIFIED_PIGLIN,
				EntityType.HUSK, EntityType.STRAY, EntityType.ENDERMAN, EntityType.PHANTOM);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.VILLAGER, EntityType.IRON_GOLEM, EntityType.SNOWMAN);
		this.addPotionEffect(CustomPotionEffect.STRENGTH_II);
	}
}
