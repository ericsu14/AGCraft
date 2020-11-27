package com.joojet.plugins.mobs.monsters.zombie;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualTravesty;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.buff.SpeedBuffSkill;
import com.joojet.plugins.music.enums.MusicType;

public class UltimateBadassZombie extends LegendaryMob
{
	public UltimateBadassZombie ()
	{
		super (MonsterType.ULTIMATE_BADASS_ZOMBIE);
		this.name = "Shadow Clone joojetsu";
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		this.setStat(MonsterStat.HEALTH, 24.0);
		
		this.addBiomes(Biome.THE_VOID);
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Equipment
		this.weapon = new SpiritualTravesty (this.color);
		this.helmet = new DarkNetheriteHelmet (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.leggings = new DarkNetheriteLeggings (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.10, 1, 2),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.30, 1, 1),
				new MonsterDrop (Material.GOLDEN_CARROT, 0.15, 3, 16));
		
		this.setStat (MonsterStat.EXPERIENCE, 50.0);
		this.bossTheme = MusicType.GORO_THEME;
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new AttackBuffSkill(1, 60));
		skills.add(new ResistanceBuffSkill (1, 60));
		skills.add(new SpeedBuffSkill (0, 60));
	}
}
