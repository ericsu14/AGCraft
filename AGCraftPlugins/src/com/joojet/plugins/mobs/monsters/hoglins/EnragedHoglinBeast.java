package com.joojet.plugins.mobs.monsters.hoglins;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;

public class EnragedHoglinBeast extends LegendaryMob
{
	public EnragedHoglinBeast () 
	{
		super (MonsterType.ENRAGED_HOGLIN_BEAST);
		this.name = "Enraged Hoglin Beast";
		this.color = ChatColor.GOLD;
		
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 30.0);
		this.setStat(MonsterStat.BASE_ARMOR, 20.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 12.0);
		this.setStat(MonsterStat.KNOCKBACK_RESISTANCE, 0.45);
		this.setStat(MonsterStat.EXPERIENCE, 60.0);
		
		this.addPotionEffect(CustomPotionEffect.SPEED, CustomPotionEffect.REGEN, 
				CustomPotionEffect.JUMP_BOOST);
		this.setStat(MonsterStat.HEALTH, 60.0);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.SHOW_NAME, MobFlag.PERSISTENT_ATTACKER);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.25, 1, 3),
				new MonsterDrop (Material.GOLD_INGOT, 0.75, 4, 7),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.25, 1, 1),
				new MonsterDrop (Material.COOKED_PORKCHOP, 1.00, 24, 32));
	}
}
