package com.joojet.plugins.mobs.monsters.creeper.julyfourth;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.JulyFourthTracerSkill;
import com.joojet.plugins.mobs.skills.visual.FireworkCreeperExplosionDeath;

public class PatrioticCreeper extends MobEquipment implements CustomSkillUser
{
	public PatrioticCreeper() 
	{
		super(MonsterType.PATRIOTIC_CREEPER);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 8);
		this.name = StringUtil.alternateTextColors("Patriotic Creeper", TextPattern.WORD, 
				ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		this.addMobFlags(MobFlag.DISABLE_EXPLOSION_GRIEFING);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new FireworkCreeperExplosionDeath());
		skills.add(new JulyFourthTracerSkill ());
	}

}
