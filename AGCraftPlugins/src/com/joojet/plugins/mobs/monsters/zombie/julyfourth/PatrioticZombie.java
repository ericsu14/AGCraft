package com.joojet.plugins.mobs.monsters.zombie.julyfourth;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.drops.FireworkDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.PatrioticBlueBoots;
import com.joojet.plugins.mobs.equipment.chest.PatrioticRedJacket;
import com.joojet.plugins.mobs.equipment.head.USAHat;
import com.joojet.plugins.mobs.equipment.leggings.PatrioticWhiteLeggings;
import com.joojet.plugins.mobs.equipment.offhand.PropFirework;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.JulyFourthTracerSkill;

public class PatrioticZombie extends MobEquipment implements CustomSkillUser
{	
	public PatrioticZombie ()
	{
		super (MonsterType.PATRIOTIC_ZOMBIE);
		this.name = StringUtil.alternateTextColors("Patriotic Zombie", 
				TextPattern.WORD, ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE);
		this.color = ChatColor.WHITE;
		this.setStat(MonsterStat.HEALTH, 4.0);
		
		this.addBiomes(Biome.THE_VOID);
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH,
				CustomPotionEffect.SPEED);
		
		this.setDropRates(0.05f, 0.03f, 0.03f, 0.03f, 0.05f, 0.75f);
		
		this.helmet = new USAHat ();
		this.chestplate = new PatrioticRedJacket ();
		this.leggings = new PatrioticWhiteLeggings();
		this.boots = new PatrioticBlueBoots ();
		
		this.offhand = new PropFirework();
		
		this.addMonsterDrops(new FireworkDrop (0.75, 16, 16));
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new JulyFourthTracerSkill ());
	}

}
