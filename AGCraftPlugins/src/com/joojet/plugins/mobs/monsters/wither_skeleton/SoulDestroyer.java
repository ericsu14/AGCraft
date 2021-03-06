package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.weapons.PerpetualTorment;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;

public class SoulDestroyer extends EpicMob 
{
	public SoulDestroyer ()
	{
		super (MonsterType.SOUL_DESTROYER);
		this.name = "Soul Destroyer";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.setDropRates(0.15f, 0.03f, 0.03f, 0.03f, 0.05f, 0.15f);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 6.0);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addMobFlags(MobFlag.IGNORE_NON_FACTION_ENTITIES, MobFlag.DISABLE_MAGIC_HEALING);
		
		this.addFactions(Faction.NETHER);
		this.addRivalFactions(Faction.DOOM_GUY, Faction.USC, Faction.UCLA, Faction.CHICKEN_GANG);
		this.addTargetsToHitList(EntityType.WITHER_SKELETON, EntityType.SKELETON, EntityType.ZOMBIE,
				EntityType.HUSK, EntityType.STRAY);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.helmet = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		this.weapon = new PerpetualTorment (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 35.0);
	}
}
