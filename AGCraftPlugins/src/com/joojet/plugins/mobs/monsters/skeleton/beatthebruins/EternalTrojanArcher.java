package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
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
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.ResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.buff.SpeedBuffSkill;

public class EternalTrojanArcher extends USCFaction 
{
	public EternalTrojanArcher ()
	{
		super (MonsterType.ETERNAL_TROJAN_ARCHER);
		this.addBiomes(Biome.THE_VOID);
		this.setStat(MonsterStat.HEALTH, 40.0);
		
		// Allows the archer to hunt creepers
		this.ignoreList.remove(EntityType.CREEPER);
		this.addTargetsToHitList(EntityType.CREEPER);
		
		this.name = StringUtil.alternateTextColors("The Eternal Spirit of Troy", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD);
		
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
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.40);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 1.00);
		
		this.addMobFlags(MobFlag.SHOW_NAME,
				MobFlag.DISABLE_SUFFOCATION_DAMAGE);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AttackBuffSkill(0));
		skills.add(new ResistanceBuffSkill (0));
		skills.add(new SpeedBuffSkill (0));
	}
}
