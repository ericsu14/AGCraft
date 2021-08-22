package com.joojet.plugins.mobs.monsters.stray;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.drops.WeightedDrop;
import com.joojet.plugins.mobs.drops.WeightedLootCrateDrop;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.enums.ThrowablePotionType;
import com.joojet.plugins.mobs.equipment.boots.MrJohnsonFeet;
import com.joojet.plugins.mobs.equipment.chest.MrJohnsonTunic;
import com.joojet.plugins.mobs.equipment.head.MrJohnsonHead;
import com.joojet.plugins.mobs.equipment.leggings.MrJohnsonLeggings;
import com.joojet.plugins.mobs.equipment.offhand.SnakeArrow;
import com.joojet.plugins.mobs.equipment.potions.MrJohnsonSpecialPotion;
import com.joojet.plugins.mobs.equipment.potions.PainfulMocktail;
import com.joojet.plugins.mobs.equipment.potions.SnakeVenomPotion;
import com.joojet.plugins.mobs.equipment.weapons.PledgeDestroyer;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.EvokerFangSkill;
import com.joojet.plugins.mobs.skills.attack.HurricaneSkill;
import com.joojet.plugins.mobs.skills.attack.ThrowEnderPearlSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.attack.anvil.AnvilThrowSkill;
import com.joojet.plugins.mobs.skills.attack.potionthrow.FireResistancePotionSkill;
import com.joojet.plugins.mobs.skills.attack.potionthrow.MrJohnsonPotionSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.passive.BlindingArrow;
import com.joojet.plugins.mobs.skills.passive.MrJohnsonAuraSkill;
import com.joojet.plugins.music.enums.MusicType;

public class MrJohnsonHidden extends EpicMob implements CustomSkillUser
{

	public MrJohnsonHidden() 
	{
		super(MonsterType.SECRET_MR_JOHNSON);
		this.color = ChatColor.DARK_PURPLE;
		this.addBiomes(Biome.THE_VOID);
		
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		this.name = StringUtil.alternateTextColors("Mr. Johnson", TextPattern.WORD, ChatColor.BLUE, ChatColor.GOLD);
		
		this.helmet = new MrJohnsonHead (this.color);
		this.chestplate = new MrJohnsonTunic (this.color);
		this.leggings = new MrJohnsonLeggings (this.color);
		this.boots = new MrJohnsonFeet (this.color);
		this.weapon = new PledgeDestroyer (this.color);
		this.tippedArrow = new SnakeArrow (this.color);
		
		this.addFactions(Faction.MR_JOHNSON);
		this.addRivalFactions(Faction.ALLIES, Faction.USC, Faction.UCLA, 
				Faction.CHICKEN_GANG, Faction.NETHER, Faction.DOOM_GUY);
		
		this.setStat(MonsterStat.HEALTH, 180.0);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.15);
		this.setStat(MonsterStat.SPAWN_LIMIT, 1);
		this.setStat(MonsterStat.SPAWN_LIMIT_COOLDOWN, 1800);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 250);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 18.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 8.0);
		
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.PLAYER, EntityType.SKELETON, EntityType.SPIDER, EntityType.STRAY, 
				EntityType.SLIME, EntityType.HUSK,EntityType.IRON_GOLEM, EntityType.SNOWMAN, EntityType.PILLAGER, EntityType.CAVE_SPIDER,
				EntityType.GIANT, EntityType.EVOKER, EntityType.VEX, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.ZOMBIFIED_PIGLIN,
				EntityType.ZOMBIE_VILLAGER, EntityType.WITHER_SKELETON);
		this.addEntitiesToIgnoreList(EntityType.CREEPER);
		
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING,
				MobFlag.DISABLE_PICK_UP_ITEMS, MobFlag.PERSISTENT_ATTACKER);
		
		// Allow Mr. Johnson to drop a random piece of diamond armor / equipment upon death
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.50, 1, 1),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 1.00, 1, 1),
				new WeightedLootCrateDrop (
						1.00, 1, 1,
						new WeightedDrop (Material.ENDER_PEARL, 60),
						new WeightedDrop (new SnakeVenomPotion (), 15),
						new WeightedDrop (new PainfulMocktail (), 15),
						new WeightedDrop (new MrJohnsonSpecialPotion(), 25),
						new WeightedDrop (new MrJohnsonHead (this.color), 15))
				);
		
		this.bossTheme = MusicType.BLINDED_BY_LIGHT;
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		// Special Mr. Johnson is able to throw a new potion
		MrJohnsonPotionSkill potionThrowSkill = new MrJohnsonPotionSkill (32, 7, Integer.MAX_VALUE, 4);
		potionThrowSkill.addPotion(new MrJohnsonSpecialPotion(), 25, ThrowablePotionType.SPLASH);
		potionThrowSkill.addPotion(new MrJohnsonSpecialPotion(), 10, ThrowablePotionType.LINGERING);
		
		skills.add(new ThundagaSkill (16, 16, Integer.MAX_VALUE, 8, 3.0F, 4, 60, 0.60));
		skills.add(new EvokerFangSkill (16, 7, Integer.MAX_VALUE, 4, 8));
		skills.add(new HurricaneSkill (8, 16, Integer.MAX_VALUE, 2, 4, 0.85));
		skills.add(new RageSkill (1, 30, 0.35));
		skills.add(new BlindingArrow (7, 6));
		skills.add(new ThrowEnderPearlSkill(72, 12, Integer.MAX_VALUE, 2, 8.0));
		skills.add(    potionThrowSkill);
		skills.add(new AnvilThrowSkill (24, 20, 2, 3.0f, 2));
		skills.add(new MrJohnsonAuraSkill ());
		skills.add(new FireResistancePotionSkill(90, Integer.MAX_VALUE, 10));
	}
}
