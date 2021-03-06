package com.joojet.plugins.mobs.monsters.skeleton;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.SkullKidBoots;
import com.joojet.plugins.mobs.equipment.chest.SkullKidChest;
import com.joojet.plugins.mobs.equipment.head.SkullKidHelmet;
import com.joojet.plugins.mobs.equipment.leggings.SkullKidPants;
import com.joojet.plugins.mobs.equipment.offhand.CursedArrow;
import com.joojet.plugins.mobs.equipment.weapons.ATerribleFate;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualTravesty;
import com.joojet.plugins.mobs.monsters.factions.classifications.MythicMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.HurricaneSkill;
import com.joojet.plugins.mobs.skills.attack.ThrowEnderPearlSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.passive.BlindingArrow;
import com.joojet.plugins.mobs.skills.utility.AggressiveTeleportSkill;
import com.joojet.plugins.music.enums.MusicType;

public class SkullKid extends MythicMob 
{
	public SkullKid ()
	{
		super (MonsterType.SKULL_KID);
		this.name = "Skull Kid";
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 100.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 1.00);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.10);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 14.0);
		this.color = ChatColor.DARK_RED;
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING, MobFlag.DISABLE_MAGIC_HEALING,
				MobFlag.HUNT_ON_SPAWN, MobFlag.BOSS_BAR, MobFlag.PERSISTENT_ATTACKER);
		
		this.setDropRates(0.25f, 0.10f, 0.10f, 0.10f, 0.05f, 0.10f);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.SPEED,
				CustomPotionEffect.UNDEAD_HEAL);
		this.addFactions(Faction.DOOM_GUY);
		this.addRivalFactions(Faction.UCLA, Faction.USC, Faction.PHANTOM, Faction.ALLIES, Faction.NETHER, Faction.CHICKEN_GANG);
		this.addTargetsToHitList(EntityType.ZOMBIFIED_PIGLIN,
				EntityType.MAGMA_CUBE, EntityType.HOGLIN, EntityType.CREEPER, EntityType.ZOMBIE, EntityType.SKELETON,
				EntityType.WITHER_SKELETON, EntityType.WITCH, EntityType.HOGLIN, EntityType.HUSK, EntityType.STRAY,
				EntityType.POLAR_BEAR, EntityType.ZOMBIE_VILLAGER, EntityType.PILLAGER, EntityType.VEX, EntityType.PIGLIN_BRUTE,
				EntityType.PIGLIN, EntityType.ENDERMAN, EntityType.RAVAGER, EntityType.DROWNED, EntityType.PHANTOM,
				EntityType.EVOKER);
		
		this.helmet = new SkullKidHelmet (this.color);
		this.chestplate = new SkullKidChest (this.color);
		this.leggings = new SkullKidPants (this.color);
		this.boots = new SkullKidBoots (this.color);
		this.weapon = new ATerribleFate (this.color);
		this.offhand = new CursedArrow (this.color);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.50, 2, 3),
				new MonsterDrop (Material.NETHERITE_INGOT, 0.05, 1, 1),
				new MonsterDrop (new SpiritualTravesty(ChatColor.GOLD), 0.01),
				new MonsterDrop (Material.DIAMOND, 0.10, 1, 5),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 1.00, 5, 7),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.60, 1, 3),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.10, 1, 2));
		
		this.setStat(MonsterStat.EXPERIENCE, 500.0);
		this.bossTheme = MusicType.KUZE_THEME;
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new RageSkill (1, 30, 0.30));
		skills.add(new AggressiveTeleportSkill (156, 10, Integer.MAX_VALUE, 2));
		skills.add(new ResistanceBuffSkill (0, 60, 20, 60, 80));
		skills.add(new ThundagaSkill (32, 20, 6, 16, 4.0f, 4, 60, 0.60));
		skills.add(new HurricaneSkill (8, 16, Integer.MAX_VALUE, 12, 4, 0.75));
		skills.add(new BlindingArrow (7, 4));
		skills.add(new ThrowEnderPearlSkill (128, 20, 5, 1, 16));
	}
}
