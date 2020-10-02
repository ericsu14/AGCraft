package com.joojet.plugins.mobs.monsters.zombie;

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
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.music.enums.MusicType;

public class Shrek extends MobEquipment
{
	public Shrek ()
	{
		super (MonsterType.SHREK);
		this.color = ChatColor.DARK_GREEN;
		this.name = "Shrek";
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		this.setStat(MonsterStat.HEALTH, 50.0);
		this.addBiomes(Biome.SWAMP, Biome.SWAMP_HILLS);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.helmet = new ShrekHat (this.color);
		this.chestplate = new OgreTunic (this.color);
		this.leggings = new OgreLeggings (this.color);
		this.boots = new OgreBoots (this.color);
		this.weapon = new OgreAxe (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 40.0);
		this.bossTheme = MusicType.SHREK;
	}
}
