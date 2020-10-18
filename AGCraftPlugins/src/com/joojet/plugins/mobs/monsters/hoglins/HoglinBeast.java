package com.joojet.plugins.mobs.monsters.hoglins;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.factions.classifications.EpicMob;

public class HoglinBeast extends EpicMob
{
	public HoglinBeast () 
	{
		super(MonsterType.HOGLIN_BEAST);
		this.name = "Hoglin Beast";
		this.color = ChatColor.LIGHT_PURPLE;
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.REGEN, CustomPotionEffect.SPEED,
				CustomPotionEffect.JUMP_BOOST);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 16.0);
		this.setStat(MonsterStat.BASE_ARMOR, 14.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 6.0);
		this.setStat(MonsterStat.EXPERIENCE, 25.0);
		
		this.addMonsterDrops(new MonsterDrop (Material.COOKED_PORKCHOP, 1.00, 2, 8),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.075, 1, 1));
	}

}
