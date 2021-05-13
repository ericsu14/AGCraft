package com.joojet.plugins.mobs.allies.wolf;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.chest.SnowballHeart;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedAttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedResistanceBuffSkill;

public class Snowball extends AlliedMob implements CustomSkillUser
{
	public Snowball ()
	{
		super (MonsterType.SNOWBALL);
		this.color = ChatColor.AQUA;
		this.name = "Snowball";
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		this.chestplate = new SnowballHeart (this.color);
		this.boots = new LetItGo (this.color);
		this.setDropRates(0.0f, 1.15f, 0.0f, 0.0f, 0.0f, 0.0f);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 8.0);
		this.setStat(MonsterStat.HEALTH, 40.0);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AlliedResistanceBuffSkill (0, 45, 15, 60, 8));
		skills.add(new AlliedAttackBuffSkill(0, 45, 15, 60, 8));
	}
}
