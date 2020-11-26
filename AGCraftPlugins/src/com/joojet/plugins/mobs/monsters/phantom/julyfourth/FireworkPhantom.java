package com.joojet.plugins.mobs.monsters.phantom.julyfourth;

import java.util.List;

import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class FireworkPhantom extends MobEquipment
{
	public FireworkPhantom ()
	{
		super (MonsterType.FIREWORK_PHANTOM);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 1.0);
		this.addMobFlags(MobFlag.FIREWORK_DEATH);
		this.name = this.americanizeText("Shoot Me!");
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		// TODO Auto-generated method stub
		
	}
}
