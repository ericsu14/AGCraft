package com.joojet.plugins.mobs.allies.snowman;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.head.ScruffyFace;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.SelfHealSkill;
import com.joojet.plugins.mobs.skills.passive.IcySnowballSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;

public class Scruffy extends AlliedMob implements CustomSkillUser
{
	public Scruffy ()
	{
		super (MonsterType.SCRUFFY);
		this.name = "Scruffy";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.DISABLE_DROWNING, 
				MobFlag.DISABLE_MELTING);
		this.addEntitiesToIgnoreList(EntityType.CREEPER);
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.WATER_BREATHING,
				CustomPotionEffect.FIRE_RESISTANCE, CustomPotionEffect.REGEN, CustomPotionEffect.SPEED);
		
		// Chestplate
		this.helmet = new ScruffyFace (this.color);
		
		// Boots
		this.boots = new LetItGo (this.color);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new ThundagaSkill (20, 14, Integer.MAX_VALUE, 12, 2.0F, 6, 20, 1.00));
		skills.add(new SelfHealSkill (2, 30, 8, 0.50));
		skills.add(new TeleportSkill (64, 60, Integer.MAX_VALUE, 1));
		skills.add(new IcySnowballSkill (1.00, 12.0));
	}
		
}
