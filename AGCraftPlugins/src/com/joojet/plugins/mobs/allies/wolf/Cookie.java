package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.allies.factions.AlliedMob;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.CookieHeart;

public class Cookie extends AlliedMob
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
		this.setStat(MonsterStat.BASE_ATTACK_DAMAGE, 12.0);
		this.setStat(MonsterStat.HEALTH, 40.0);
	}
}
