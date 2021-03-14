package com.joojet.plugins.mobs.monsters.stray.hungergames;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.drops.LootCrateDrop;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.MrJohnsonFeet;
import com.joojet.plugins.mobs.equipment.chest.MrJohnsonTunic;
import com.joojet.plugins.mobs.equipment.head.MrJohnsonHead;
import com.joojet.plugins.mobs.equipment.leggings.MrJohnsonLeggings;
import com.joojet.plugins.mobs.equipment.weapons.VeryPotentBow;
import com.joojet.plugins.mobs.interfaces.CustomSpawnMessage;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.EvokerFangSkill;
import com.joojet.plugins.mobs.skills.attack.HurricaneSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.buff.RageSkill;
import com.joojet.plugins.mobs.skills.passive.BlindingArrow;
import com.joojet.plugins.mobs.skills.passive.NerfDamageOutputSkill;
import com.joojet.plugins.music.enums.MusicType;

public class HGMrJohnson extends MobEquipment implements CustomSpawnMessage
{

	public HGMrJohnson() 
	{
		super(MonsterType.HG_MR_JOHNSON);
		this.color = ChatColor.DARK_PURPLE;
		this.addBiomes(Biome.THE_VOID);
		
		this.name = StringUtil.alternateTextColors("Mr. Johnson", TextPattern.WORD, ChatColor.DARK_BLUE, ChatColor.GOLD);
		
		this.helmet = new MrJohnsonHead (this.color);
		this.chestplate = new MrJohnsonTunic (this.color);
		this.leggings = new MrJohnsonLeggings (this.color);
		this.boots = new MrJohnsonFeet (this.color);
		this.weapon = new VeryPotentBow (this.color);
		
		this.setStat(MonsterStat.HEALTH, 12.0);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.15);
		this.setStat(MonsterStat.SPAWN_LIMIT, 2);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 150);
		
		this.addMobFlags(MobFlag.BOSS_BAR, MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING,
				MobFlag.DISABLE_PICK_UP_ITEMS);
		
		// Allow Mr. Johnson to drop a random piece of diamond armor / equipment upon death
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.20, 1, 3),
				new MonsterDrop (Material.GOLDEN_APPLE, 0.75, 1, 1),
				new LootCrateDrop (1.00, 1, 1, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE,
						Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD));
		
		this.bossTheme = MusicType.HAIKYUU;
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		skills.add(new ThundagaSkill (8, 12, Integer.MAX_VALUE, 8, 1.5F, 4, 60, 0.60));
		skills.add(new EvokerFangSkill (8, 8, Integer.MAX_VALUE, 4, 0));
		skills.add(new HurricaneSkill (12, 16, Integer.MAX_VALUE, 2, 3, 0.60));
		skills.add(new RageSkill (0, 30, 0.35));
		skills.add(new NerfDamageOutputSkill (0.50));
		skills.add(new BlindingArrow (7, 6));
	}

	@Override
	public String getSpawnMessage() 
	{
		return ChatColor.GOLD + "A powerful monster spawned into the arena! Eliminate him to get powerful equipment!";
	}
	
}