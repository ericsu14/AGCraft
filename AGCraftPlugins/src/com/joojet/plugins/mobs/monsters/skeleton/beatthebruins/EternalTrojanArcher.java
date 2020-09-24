package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.horse.beatthebruins.TheTraveler;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCBandUniformTop;
import com.joojet.plugins.mobs.equipment.head.USCBandHead;
import com.joojet.plugins.mobs.equipment.leggings.USCBandUniformBottom;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.EternalSpiritOfTroy;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class EternalTrojanArcher extends USCFaction 
{
	public EternalTrojanArcher ()
	{
		super (MonsterType.ETERNAL_TROJAN_ARCHER);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 40.0);
		
		this.name = ChatColor.RED + "Eternal"
				+ ChatColor.GOLD + " Trojan"
				+ ChatColor.RED + " Archer";
		
		this.color = ChatColor.GOLD;
		this.mount = new MountedMob (EntityType.HORSE, new TheTraveler());
		
		this.weapon = new EternalSpiritOfTroy ();
		this.offhand = new USCCreeperShield ();
		this.helmet = new USCBandHead (this.color);
		this.chestplate = new USCBandUniformTop (this.color);
		this.leggings = new USCBandUniformBottom (this.color);
		this.boots = new USCSpikedBoots (this.color);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 12.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.50);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 1.00);
		
		this.addMobFlags(MobFlag.SHOW_NAME,
				MobFlag.DISABLE_SUFFOCATION_DAMAGE);
	}
}
