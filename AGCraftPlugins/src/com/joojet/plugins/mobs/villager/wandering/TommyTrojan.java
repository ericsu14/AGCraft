package com.joojet.plugins.mobs.villager.wandering;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.USCSpikedBoots;
import com.joojet.plugins.mobs.equipment.chest.USCBandUniformTop;
import com.joojet.plugins.mobs.equipment.chest.USCFootballTunic;
import com.joojet.plugins.mobs.equipment.head.USCBandHead;
import com.joojet.plugins.mobs.equipment.head.USCTrojan;
import com.joojet.plugins.mobs.equipment.leggings.USCBandUniformBottom;
import com.joojet.plugins.mobs.equipment.leggings.USCFootballTrousers;
import com.joojet.plugins.mobs.equipment.offhand.USCCreeperShield;
import com.joojet.plugins.mobs.equipment.weapons.EternalSpiritOfTroy;
import com.joojet.plugins.mobs.equipment.weapons.EternalTrojanSword;
import com.joojet.plugins.mobs.equipment.weapons.FightOn;
import com.joojet.plugins.mobs.equipment.weapons.TrojanSword;
import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.UCLAArcher;
import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.UCLAJock;
import com.joojet.plugins.mobs.scrolls.BossScroll;
import com.joojet.plugins.mobs.scrolls.SummonEternalTrojanArcher;
import com.joojet.plugins.mobs.scrolls.SummonGiantBruinTamer;
import com.joojet.plugins.mobs.scrolls.SummonSpiritOfTroy;
import com.joojet.plugins.mobs.scrolls.SummonTrojanWarrior;
import com.joojet.plugins.mobs.scrolls.SummonUCLABearTamer;
import com.joojet.plugins.mobs.scrolls.SummonUSCArcher;
import com.joojet.plugins.mobs.scrolls.SummonUSCWarrior;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedAttackBuffSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedResistanceBuffSkill;
import com.joojet.plugins.mobs.skills.buff.AlliedSpeedBuffSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

public class TommyTrojan extends VillagerEquipment {

	public TommyTrojan() 
	{
		super(MonsterType.TOMMY_TROJAN);
		this.name = StringUtil.alternateTextColors("Tommy Trojan", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD);
		this.addMobFlags(MobFlag.SHOW_NAME);
		this.setStat(MonsterStat.HEALTH, 40);
		
		// Normal AGvillager trades
		this.addRecipe(new ItemStack (Material.GOLDEN_CARROT, 16), Material.EMERALD, 2, 4);
		this.addRecipe(new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 1), Material.EMERALD, 16, 4);
		this.addRecipe(new ItemStack (Material.EMERALD, 16), Material.DIAMOND, 4, 4);
		this.addRecipe(new ItemStack (Material.NETHERITE_INGOT, 1), Material.DIAMOND, 10, 2);
		
		// Rare USC Themed weapons
		this.addRecipe(new USCCreeperShield (), Material.EMERALD, 36, 1);
		this.addRecipe(new TrojanSword (ChatColor.GOLD), Material.EMERALD, 48, 1);
		this.addRecipe(new FightOn (ChatColor.GOLD), Material.EMERALD, 48, 1);
		this.addRecipe(new EternalTrojanSword (), Material.DIAMOND, 48, 1);
		this.addRecipe(new EternalSpiritOfTroy (), Material.DIAMOND, 54, 1);
		
		// USC Themed armor
		this.addRecipe(new USCFootballTunic (ChatColor.GOLD), Material.EMERALD, 32, 1);
		this.addRecipe(new USCBandUniformTop (ChatColor.GOLD), Material.EMERALD, 32, 1);
		this.addRecipe(new USCFootballTrousers (ChatColor.GOLD), Material.EMERALD, 32, 1);
		this.addRecipe(new USCBandUniformBottom (ChatColor.GOLD), Material.EMERALD, 32, 1);
		this.addRecipe(new USCSpikedBoots (ChatColor.GOLD), Material.EMERALD, 48, 1);
		this.addRecipe(new USCBandHead (ChatColor.GOLD), Material.EMERALD, 48, 1);
		this.addRecipe(new USCTrojan (ChatColor.GOLD), Material.EMERALD, 48, 1);
		
		// USC Summoning scrolls
		this.addRecipe(new SummonUSCArcher (), Material.EMERALD, 16, 3);
		this.addRecipe(new SummonUSCWarrior (), Material.EMERALD, 16, 3);
		this.addRecipe(new SummonSpiritOfTroy(), Material.EMERALD, 16, 3);
		this.addRecipe(new SummonEternalTrojanArcher(), Material.DIAMOND, 8, 2);
		this.addRecipe(new SummonTrojanWarrior (), Material.DIAMOND, 8, 2);
		
		// UCLA Summoning scrolls
		this.addRecipe(new BossScroll (new UCLAJock (), EntityType.HUSK), Material.EMERALD, 8, 5);
		this.addRecipe(new BossScroll (new UCLAArcher (), EntityType.HUSK), Material.EMERALD, 8, 5);
		this.addRecipe(new SummonUCLABearTamer (), Material.EMERALD, 64, 1);
		this.addRecipe(new SummonGiantBruinTamer(), Material.NETHERITE_INGOT, 2, 1);
		
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new AlliedAttackBuffSkill(0, 60, 15, 60, 8));
		skills.add(new AlliedResistanceBuffSkill (0, 60, 15, 60, 8));
		skills.add(new AlliedSpeedBuffSkill (0, 60, 15, 60, 8));
		skills.add(new TeleportSkill (64, 30, Integer.MAX_VALUE, 2));
	}

}
