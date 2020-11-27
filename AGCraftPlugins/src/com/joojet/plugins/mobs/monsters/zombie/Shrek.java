package com.joojet.plugins.mobs.monsters.zombie;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.OgreBoots;
import com.joojet.plugins.mobs.equipment.chest.OgreTunic;
import com.joojet.plugins.mobs.equipment.head.ShrekHat;
import com.joojet.plugins.mobs.equipment.leggings.OgreLeggings;
import com.joojet.plugins.mobs.equipment.weapons.OgreAxe;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.buff.SpeedBuffSkill;
import com.joojet.plugins.music.enums.MusicType;

public class Shrek extends LegendaryMob
{
	public Shrek ()
	{
		super (MonsterType.SHREK);
		this.color = ChatColor.DARK_GREEN;
		this.name = "Shrek";
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		this.setStat(MonsterStat.HEALTH, 60.0);
		this.addBiomes(Biome.SWAMP, Biome.SWAMP_HILLS);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.helmet = new ShrekHat (this.color);
		this.chestplate = new OgreTunic (this.color);
		this.leggings = new OgreLeggings (this.color);
		this.boots = new OgreBoots (this.color);
		this.weapon = new OgreAxe (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 60.0);
		this.bossTheme = MusicType.SHREK;
	}
	
	@Override
	public void loadCustomSkills (List <AbstractSkill> skills)
	{
		skills.add(new AttackBuffSkill(2, 45, 60, 20, 8));
		skills.add(new ResistanceBuffSkill (1, 60, 60, 20, 8));
		skills.add(new SpeedBuffSkill (0, 60, 60, 20, 8));
	}
}
