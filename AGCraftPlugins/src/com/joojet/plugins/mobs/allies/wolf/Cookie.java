package com.joojet.plugins.mobs.allies.wolf;

import java.util.List;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.CookieHeart;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedAttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedResistanceBuffSkill;

public class Cookie extends AlliedMob implements CustomSkillUser
{
	public Cookie ()
	{
		super (MonsterType.COOKIE);
		this.setDropRates(0.00f, 1.15f, 0.00f, 0.00f, 0.00f, 0.00f);
		this.color = ChatColor.GOLD;
		this.name = "Cookie";
		this.addPotionEffect(CustomPotionEffect.REGEN, CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.FULL_HEALING, CustomPotionEffect.RESISTANCE_II);
		this.chestplate = new CookieHeart (this.color);
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 8.0);
		this.setStat(MonsterStat.HEALTH, 40.0);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AlliedAttackBuffSkill(0, 45, 15, 60, 8));
		skills.add(new AlliedResistanceBuffSkill (0, 45, 15, 60, 8));
	}
}
