package com.joojet.plugins.mobs.monsters.polar_bear.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;

public class TheBruinBear extends UCLAFaction
{
	public TheBruinBear ()
	{
		super (MonsterType.BRUIN_BEAR);
		this.color = ChatColor.AQUA;
		this.name = "The" + ChatColor.GOLD + " Bruin" + this.color + " Bear";
		this.setStat(MonsterStat.HEALTH, 60.0);
		this.addTargetsToHitList(EntityType.CREEPER, EntityType.IRON_GOLEM);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 20.0);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.SHOW_NAME, MobFlag.PERSISTENT_ATTACKER);
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.STRENGTH_II, CustomPotionEffect.JUMP_BOOST,
				CustomPotionEffect.SPEED, CustomPotionEffect.RESISTANCE_II, CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.REGEN);
		this.setStat(MonsterStat.EXPERIENCE, 200.0);
		this.ignoreList.clear();
	}
}
