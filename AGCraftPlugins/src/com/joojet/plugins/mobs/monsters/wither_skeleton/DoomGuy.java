package com.joojet.plugins.mobs.monsters.wither_skeleton;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.DoomGuyFeet;
import com.joojet.plugins.mobs.equipment.chest.DoomChest;
import com.joojet.plugins.mobs.equipment.head.DoomSlayerHead;
import com.joojet.plugins.mobs.equipment.leggings.DoomGuyLegs;
import com.joojet.plugins.mobs.equipment.offhand.ArrowOfDoom;
import com.joojet.plugins.mobs.equipment.weapons.DoomBlade;
import com.joojet.plugins.mobs.equipment.weapons.DoomBow;
import com.joojet.plugins.mobs.monsters.factions.classifications.MythicMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.HurricaneSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.utility.AgressiveTeleportSkill;
import com.joojet.plugins.mobs.skills.utility.WeaponSwitchSkill;
import com.joojet.plugins.music.enums.MusicType;

public class DoomGuy extends MythicMob
{
	public DoomGuy ()
	{
		super (MonsterType.DOOM_GUY);
		this.name = StringUtil.alternateTextColors("The Doom Slayer", TextPattern.WORD, 
				ChatColor.DARK_GRAY, ChatColor.DARK_RED);
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.05f, 1.00f);
		this.color = ChatColor.DARK_RED;
		this.setStat(MonsterStat.HEALTH, 50.0);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 12.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 125.0);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 24.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 1.00);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.40);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.UNDEAD_HEAL, CustomPotionEffect.STRENGTH);
		
		this.addFactions(Faction.DOOM_GUY);
		this.addRivalFactions(Faction.NETHER, Faction.USC, Faction.UCLA);
		this.addTargetsToHitList(EntityType.ZOMBIFIED_PIGLIN,
				EntityType.MAGMA_CUBE, EntityType.HOGLIN, EntityType.CREEPER, EntityType.ZOMBIE, EntityType.SKELETON,
				EntityType.WITHER_SKELETON, EntityType.WITCH, EntityType.HOGLIN, EntityType.HUSK, EntityType.STRAY,
				EntityType.POLAR_BEAR);
		
		this.addMobFlags(MobFlag.SPAWN_LIGHTNING, MobFlag.SHOW_NAME, MobFlag.BOSS_BAR,
				MobFlag.PERSISTENT_ATTACKER);
		
		this.tippedArrow = new ArrowOfDoom (this.color);
		
		this.helmet = new DoomSlayerHead (this.color);
		this.chestplate = new DoomChest (this.color);
		this.leggings = new DoomGuyLegs (this.color);
		this.boots = new DoomGuyFeet (this.color);
		this.weapon = new DoomBow (this.color);
		this.offhand = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 1.00, 2, 8),
				new MonsterDrop (Material.NETHERITE_INGOT, 1.00, 1, 1),
				new MonsterDrop (Material.DIAMOND, 0.75, 1, 4),
				new MonsterDrop (Material.EXPERIENCE_BOTTLE, 1.00, 5, 7),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 1.00, 2, 6),
				new MonsterDrop (new DoomBow (this.color), 0.02));
		
		this.bossTheme = MusicType.DOOM_GUY;
		this.setStat(MonsterStat.EXPERIENCE, 600.0);
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new RageSkill (1, 60, 0.30));
		skills.add(new AgressiveTeleportSkill (156, 10, Integer.MAX_VALUE, 2));
		// Allows doom guy to switch to its blade once its health reaches below 40%
		skills.add(new WeaponSwitchSkill (200, 1, 6, new DoomBlade (this.color)) 
		{
			@Override
			protected void playAnimation(LivingEntity caster, DamageDisplayListener damageDisplayListener) {
				caster.getLocation().getWorld().spawnParticle(Particle.ASH, caster.getLocation(), 10, 0.1, 0.1, 0.1);
				caster.getLocation().getWorld().spawnParticle(Particle.SPELL_INSTANT, caster.getLocation(), 10, 0.1, 0.1, 0.1);
				caster.getLocation().getWorld().playSound(caster.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);
				damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + "" + ChatColor.BOLD + "CQB ENGAGED!");
			}

			@Override
			protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies,
					List<LivingEntity> enemies) {
				return true;
			}

			@Override
			protected boolean checkConditions(LivingEntity caster) {
				return this.checkHealthIsBelowThreshold(caster, 0.40);
			}
			
		});
		skills.add(new HurricaneSkill (24, 20, Integer.MAX_VALUE, 4, 16, 0.75));
	}
	
}
