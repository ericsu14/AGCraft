package com.joojet.plugins.mobs.monsters.hoglins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.visual.FireAura;

public class EnragedHoglinBeast extends LegendaryMob
{
	public EnragedHoglinBeast () 
	{
		super (MonsterType.ENRAGED_HOGLIN_BEAST);
		this.name = "Enraged Hoglin Beast";
		this.color = ChatColor.GOLD;
		this.addBiomes(Biome.THE_VOID);
		
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 16.0);
		this.setStat(MonsterStat.BASE_ARMOR, 16.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 8.0);
		this.setStat(MonsterStat.KNOCKBACK_RESISTANCE, 0.45);
		this.setStat(MonsterStat.EXPERIENCE, 60.0);
		this.setStat(MonsterStat.SPAWN_LIMIT, 5);
		this.setStat(MonsterStat.SPAWN_LIMIT_COOLDOWN, 180);
		
		this.addPotionEffect(CustomPotionEffect.SPEED, CustomPotionEffect.JUMP_BOOST);
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.SHOW_NAME, MobFlag.PERSISTENT_ATTACKER);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.25, 1, 3),
				new MonsterDrop (Material.GOLD_INGOT, 0.75, 4, 7),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.25, 1, 1),
				new MonsterDrop (Material.COOKED_PORKCHOP, 1.00, 16, 24));
	}
	
	/** Allow the enraged hoglin beast to have the fire aura effect */
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		super.loadCustomSkills(skills);
		skills.add(new FireAura ());
	}
}
