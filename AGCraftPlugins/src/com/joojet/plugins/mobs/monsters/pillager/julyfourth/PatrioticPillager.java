package com.joojet.plugins.mobs.monsters.pillager.julyfourth;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.weapons.FireworkLauncher;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.JulyFourthTracerSkill;

public class PatrioticPillager extends MobEquipment implements CustomSkillUser
{
	public PatrioticPillager ()
	{
		super (MonsterType.PATRIOTIC_PILLAGER);
		this.addBiomes(Biome.THE_VOID);
		
		this.addMobFlags(MobFlag.RANDOM_FIREWORK_ON_OFFHAND);
		
		this.name = StringUtil.alternateTextColors("Patriotic Pillager", 
				TextPattern.WORD, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.weapon = new FireworkLauncher (ChatColor.GOLD);
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.85f);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new JulyFourthTracerSkill ());
	}
}
